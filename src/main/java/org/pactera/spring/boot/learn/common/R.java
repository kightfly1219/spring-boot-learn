package org.pactera.spring.boot.learn.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.nio.file.AccessDeniedException;

@Data
public class R<T> {
    private int code;

    private String msg;

    private T data;

    public R() {

    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> R<T> ok(T data) {
        return new R<>(200, "操作成功", data);
    }

    public static R<Boolean> ok() {
        return new R<>(200, "操作成功", true);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(200, msg, data);
    }

    public static R<Boolean> error() {
        return new R<Boolean>(500, "操作失败", false);
    }

    public static R<Boolean> error(Exception ex) {
        R r = new R();
        r.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        r.msg = ex.getMessage();
        r.data = false;
        return r;
    }

    public static R<Boolean> error(AccessDeniedException ex) {
        R r = new R();
        r.code = HttpStatus.FORBIDDEN.value();
        r.msg = ex.getMessage();
        r.data = false;
        return r;
    }
}
