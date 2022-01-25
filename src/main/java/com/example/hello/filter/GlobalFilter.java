package com.example.hello.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j

//FilterApplication에 @ServletComponentScan를 붙이면 @Component생략가능
//@Component

// 특정 URI만 적용하는 방법, 배열로 사용하여 여러개 적용도 가능
@WebFilter(urlPatterns = "/filter/user/*")

public class GlobalFilter implements Filter {

    // request -> filter -> controller -> filter -> response
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
        // HttpServletRequest로 캐스팅을 하면
        // 전처리에서 stream(1회성)으로 다 읽어서 실제 response로 return 할 내용이 없어진다.
//         HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//         HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // stream - > contentCaching으로 재사용가능
        // ContentCachingRequestWrapper의 생성자를 보게되면 길이만 설정할 뿐 내용은 담고 있지 않다.
        // 즉 전처리에서 내용을 일게되면 cacheing을 하는 이유가 없어진다.
        // controller에서 내용을 수행해야 정상적으로 사용가능
//        BufferedReader br = httpServletRequest.getReader();
//        br.lines().forEach(line->{
//            log.info("url : {}, line : {}", url, line);
//        });


        // ContentCachingRequestWrapper 생성자 에서는 cachedContent 크기에 대한 설정만 한다(내용은 들어가지 않는다.)
        // 실질적인 내용은 controller에서 body를 읽어야만 내용이 caching 된다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);




        // 기준점 : controller로 내용을 수행한다.
//         chain.doFilter(request, response);
        chain.doFilter(httpServletRequest, httpServletResponse);

        //후처리
        //request

        String url = httpServletRequest.getRequestURI();


        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        // response를 이미 읽어버렸기 때문에 request와 마찬가지로 clinent는 받는 내용이 없다.
        // 이를 방지하기 위해 다음 메서드를 사용
        // 기준점에서 response을 넣게 되면 담겨지는 내용이 없다  ---> 캐싱 기능을 적용못했기 때문
        httpServletResponse.copyBodyToResponse();
        log.info("response status : {}, responseBody : {}", httpStatus, resContent);



    }

}
