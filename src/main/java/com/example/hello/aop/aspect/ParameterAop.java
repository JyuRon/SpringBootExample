package com.example.hello.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

// aop로 동작시키면서
@Aspect
//스프링에서 관리시킨다
@Component
public class ParameterAop {

    // 어떠한 부분에 적용시킬 것인지 정한다(외부에서 바라보는 시각으로 : aop)
    // 표현식의 경우 검색하면서
    // 해당내용은 controller패키지 하위의 모든 클래스에 적용
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){
        // 별짓을 해봐도 이건 실행안됨
        System.out.println("cutcutdut");
    }

    // 함수의 첫 시작을 이걸로 한다고 생각
    // 매개변수를 받아 온 이후 실행된다.
    @Before("cut()")
    public void before(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());
        Object[] args = joinPoint.getArgs();


        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }

    }

    // cut메소드가 controller, service에서 실행완료 후 return 하는 값을 afterReturn 메소드도 obj라는 이름으로 받아온다.
    @AfterReturning(value = "cut()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj){
        System.out.println("return obj");
        System.out.println(obj);

    }
}
