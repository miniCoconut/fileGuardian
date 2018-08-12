package com.miniye.guardian.model;

import com.miniye.guardian.proxy.ProxyFactory;
import com.miniye.guardian.utils.ReflectionUtils;

import java.lang.reflect.Method;

public class ClassBean {
    private Class cls;
    private Object instance;

    public Class getCls() {
        return cls;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public Method[] getMethods() {
        return cls.getMethods();
    }

    public Object getInstance() {
        return instance;
    }

    public void newInstance4ConstructorWithArgs(Class[] parameterTypes, Object[] parameters) throws Exception {
        this.instance = ReflectionUtils.newInstance4ConstructorWithArgs(this.cls, parameterTypes, parameters);
    }

    public Object getProxy() throws InstantiationException {
        if(instance == null) {
            throw new InstantiationException("[getProxy]: can't get proxy with null instance! ");
        }
        Object proxy = new ProxyFactory(instance);
        return proxy;
    }

    public Object invokeStaticMethod(String methodName, Class[] parameterTypes, Object... parameters) throws Exception {
        return ReflectionUtils.invokeStaticMethod(this.cls, methodName, parameterTypes, parameters);
    }

    public Object invokeNonStaticMethod(String methodName, Class[] parameterTypes, Object... parameters) throws Exception {
        if(instance == null) {
            throw new InstantiationException("[invokeNonStaticMethod]: can't invoke non-static method with null instance! ");
        }
        return ReflectionUtils.invokeNonStaticMethod(this.cls, this.instance, methodName, parameterTypes, parameters);
    }

    @Override
    public String toString() {
        return "ClassBean for Class " + cls.getName();
    }
}
