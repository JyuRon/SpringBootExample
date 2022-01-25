package com.example.hello.interceptor.interceptor;


import com.example.hello.interceptor.annotation.Auth;
import com.example.hello.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 현재 프로젝트에서는 filter -> GlobalFilter를 적용하여 controller에서 처리한 내용이 caching 이 되어 있는 상태이다.
        // 즉 Filter단에서 ContentCachingRequestWrapper를 사용하여 객체를 doFilter 메소드로 넘겨주면
        // (ContentCachingRequestWrapper) request로 형변환이 가능하며 캐싱 또한 사용가능하다.
        // preHandle 에서는 controller 진입 전이기 때문에 해당기능을 사용하지 못한다.
        ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
        String reqContent = new String(requestWrapper.getContentAsByteArray());
        String url = request.getRequestURI();
        log.info("request url : {}, request body : {}", url, reqContent);
        HandlerInterceptor.super.postHandle(requestWrapper, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toUri();
        System.out.println(uri);
        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public으로 동작을 하는데
        // 단! Auth 권하을 가진 요청에 대해서는 세션, 쿠키
        if(hasAnnotation){
            // 권한체크
            String query = uri.getQuery();
            log.info("query : {}", query);

            if(query.equals("name=steve")){
                return true;
            }

            throw new AuthException();
        }

        return true;
    }


    private boolean checkAnnotation(Object handler, Class clazz){


        // resource javascript, html
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod)handler;

        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation 이 있을때는 true
            return true;
        }

        return false;
    }
}
