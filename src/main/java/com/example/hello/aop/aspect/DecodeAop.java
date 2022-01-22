package com.example.hello.aop.aspect;


import com.example.hello.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.hello.aop.controller..*.*(..))")
    private void cut(){}

    @Pointcut("@annotation(com.example.hello.aop.annotation.Decode)")
    private void enableDecode(){}

    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        // request에서 암호화된 이메일을 받는다
        Object[] args = joinPoint.getArgs();

        for(Object arg : args){
            if(arg instanceof User){
                User user = User.class.cast(arg);

                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                user.setEmail(email);
            }
        }

    }
    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) throws UnsupportedEncodingException {
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);

            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }

    }
}
