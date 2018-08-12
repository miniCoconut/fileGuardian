package com.miniye.guardian.proxy;

public class ProxyFactory {
    private Object proxy;

    public ProxyFactory(Object target) {
        if(target.getClass().isAssignableFrom(Object.class)) {
            this.proxy = new JDKProxyFactory(target);
        } else {
            this.proxy = new CglibProxyFactory(target);
        }
    }

    public Object getProxy() {
        return proxy;
    }
}
