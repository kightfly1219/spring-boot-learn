package org.pactera.spring.boot.learn.exception;

import org.pactera.spring.boot.learn.common.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<R<Boolean>> handleServiceException(ServiceException e) {
        // 在这里可以根据具体需求进行异常处理逻辑
        System.out.println("全局异常拦截器捕获");

        // 返回适当的HTTP状态码和错误信息
        return new ResponseEntity<>(R.error(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<R<Boolean>> handleAccessDeniedException(AccessDeniedException e) {
        // 在这里可以根据具体需求进行异常处理逻辑
        System.out.println("权限异常拦截器捕获");

        // 返回适当的HTTP状态码和错误信息
        return new ResponseEntity<>(R.error(e), HttpStatus.FORBIDDEN);
    }
}
