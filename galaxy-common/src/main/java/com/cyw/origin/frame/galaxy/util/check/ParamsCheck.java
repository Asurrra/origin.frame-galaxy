package com.cyw.origin.frame.galaxy.util.check;

import java.lang.reflect.Field;

/**
 * 参数校验包装类
 *
 * @author yiwen.chang
 * @version 1.3.0
 */
public class ParamsCheck {
    /**
     * 多参数校验,基本类型不能使用此方法
     *
     * @param obj
     * @param params
     * @return
     */
    public static String checkParams(Object obj, String... params) {
        if (obj == null) {
            return "对象";
        }
        try {
            Class<?> clazz = obj.getClass();
            for (String param : params) {
                Field field = clazz.getDeclaredField(param);
                field.setAccessible(true);
                Object oo = field.get(obj);
                if (oo == null || oo.equals("")) {
                    String name = field.getName();
                    return name;
                }
            }
        } catch (Exception e) {
            return "参数类型";
        }
        return "0";
    }

    /**
     * 基本类型包装类单参数校验是否不为空
     *
     * @param obj
     * @return
     */
    public static Boolean checkParam(Object obj) {
        return obj != null && !"".equals(obj);
    }

    /**
     * 多参数校验仅此参数不为空,基本类型不能使用此方法(params必须按照实体顺序)
     *
     * @param obj
     * @param params
     * @return
     */
    public static Boolean checkOnlyParams(Object obj, String eparams, String... params) {
        if (obj == null) {
            return false;
        }
        try {
            StringBuffer ps = new StringBuffer();
            StringBuffer name = new StringBuffer();
            for (String param : params) {
                ps.append(param);
            }
            ps.append(eparams);
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (checkParam(field.get(obj))) {
                    name.append(field.getName());
                }
            }
            if (ps.toString().equals(name.toString())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}

