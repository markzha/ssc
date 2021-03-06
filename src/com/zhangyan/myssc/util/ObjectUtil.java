package com.zhangyan.myssc.util;

import java.util.Collection;
import java.util.Map;

public class ObjectUtil  {

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isBlank(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isBlank(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}
