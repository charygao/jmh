
package org.openjdk.jmh.runner.link;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class ClassConventions {

    private static final Map<Method, String> METHOD_NAMES = new HashMap<>();

    public static String getMethodName(Method m) {
        String result = METHOD_NAMES.get(m);
        if (result == null) {
            StringBuilder builder = new StringBuilder();
            builder.append(m.getName());
            for (Class<?> paramType : m.getParameterTypes()) {
                builder.append(paramType.getName());
                builder.append(",");
            }
            result = builder.toString();
            METHOD_NAMES.put(m, result);
        }
        return result;
    }

}
