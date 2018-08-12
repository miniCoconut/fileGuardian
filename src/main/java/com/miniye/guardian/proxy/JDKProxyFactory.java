package com.miniye.guardian.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyFactory {
    private Object target;

    public JDKProxyFactory(Object target) {
        this.target =target;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object result = method.invoke(target, args);
                        return result;
                    }
                }
        );
    }

}
