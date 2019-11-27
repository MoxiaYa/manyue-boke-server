package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class M extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public M() {
		put("code", 200);
		put("message", "success");
	}
	
	public static M error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static M error(String msg) {
        return error(1, msg);
    }


    public static M error(int code, String msg) {
        M r = new M();
        r.put("code", code);
        r.put("message", msg);
        return r;
    }

    public static M ok(String msg) {
        M r = new M();
        r.put("message", msg);
        return r;
    }

    public static M ok(Map<String, Object> map) {
        M r = new M();
        r.putAll(map);
        return r;
    }

    /**
     * 返回结果
     *
     * @param returnMap
     * @return
     */
    public static M success(Object returnMap) {
        M r = new M();
        r.put("data", returnMap);
        return r;
    }

    public int getCode() {
        return (int) this.get("code");
    }

    public String getMsg() {
        return (String) this.get("message");
    }

    public Object getData() {
        return this.get("data");
    }

    public static M ok() {
        return new M();
    }

    @Override
    public M put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
