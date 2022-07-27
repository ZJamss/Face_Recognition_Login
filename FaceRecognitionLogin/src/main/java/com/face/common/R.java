package com.face.common;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @Program: StudentInfoConvertDemo
 * @Description:
 * @Author: ZJamss
 * @Create: 2022-06-23 16:04
 **/

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private int access = 2;

    public int isAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(400, "未知异常");
    }

    public static R init(boolean b) {
        if (b) return R.ok();
        else return R.error();
    }

    public static R error(String msg) {
        return error(400, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("code",200);
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        R r = new R();
        r.put("code", 200);
        r.put("msg", "success");
        return r;
    }

    public static R ok(int code) {
        R r = new R();
        r.put("code", code);
        r.put("msg", "success");
        return r;
    }

    public static R ok(String msg,int code) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}