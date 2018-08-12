package com.miniye.guardian.utils;

import java.beans.MethodDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReflectionUtils {
    public static Object newInstance4ConstructorWithArgs(Class cls, Class[] parameterTypes, Object... parameters) throws Exception {
        Constructor constructor = cls.getConstructor(parameterTypes);
        return constructor.newInstance(parameters);

    }

    public static Object invokeStaticMethod(Class cls, String methodName, Class[] parameterTypes, Object... parameters) throws Exception{
        Method method =cls.getMethod(methodName, parameterTypes);
        return method.invoke(null, parameters);
    }

    public static Object invokeNonStaticMethod(Class cls, Object instance, String methodName, Class[] parameterTypes, Object... parameters) throws Exception{
        Method method =cls.getMethod(methodName, parameterTypes);

        return method.invoke(instance, parameters);
    }


}
