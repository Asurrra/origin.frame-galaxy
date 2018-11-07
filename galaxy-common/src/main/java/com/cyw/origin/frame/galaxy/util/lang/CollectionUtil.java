package com.cyw.origin.frame.galaxy.util.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {

    public static boolean isEmpty(final Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(final Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 获取不为null的集合List
     * 如果coll is null， 返回一个List对象
     * 否则， 返回 coll自身
     * @param coll
     * @return
     */
    public static <T> List<T> getNotNullList(final List<T> coll) {
        if(coll == null){
        	return new ArrayList<T>();
        }
        
        return coll;
    }
    
}
