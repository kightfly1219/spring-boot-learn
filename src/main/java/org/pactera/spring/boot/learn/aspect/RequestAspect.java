package org.pactera.spring.boot.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestAspect {

    @Before("execution(* org.pactera.spring.boot.learn.controller.*.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        // 获取方法的参数
        Object[] args = joinPoint.getArgs();

        // 获取方法
        String methodName = joinPoint.getSignature().toShortString();

        // 打印请求日志
        System.out.println("Calling method: " + methodName);
        if (args.length > 0) {
            System.out.println("Method arguments: " + argsToString(args));
        }
    }

    // 辅助方法，将参数数组转换为字符串
    private String argsToString(Object[] args) {
        StringBuilder result = new StringBuilder("[");
        for (Object arg : args) {
            result.append(arg).append(", ");
        }
        if (args.length > 0) {
            // 移除最后的逗号和空格
            result.setLength(result.length() - 2);
        }
        result.append("]");
        return result.toString();
    }
}
