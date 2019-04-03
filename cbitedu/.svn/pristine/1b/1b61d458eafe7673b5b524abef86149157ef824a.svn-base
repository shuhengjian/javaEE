package com.creatorblue.cbitedu.core.utils;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

import org.springframework.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;


/**
* @ClassName: AopTargetUtils
* @Description: 代理对象获取工具
* @author tbw
* @date 2014-9-2 下午8:14:03
 */
public class AopTargetUtils {
    /**
     * 获取jdk或cglib代理的原始对象
     * @param proxy
     * @return
     * @throws Exception
     */
    public static Object getProxyTarget(Object proxy) throws Exception {
        if (!Proxy.isProxyClass(proxy.getClass()) &&
                !ClassUtils.isCglibProxy(proxy)) {
            return proxy;
        }

        if (Proxy.isProxyClass(proxy.getClass())) {
            return ReflectHelper.getValueByFieldName(proxy, "h");
        } else if (ClassUtils.isCglibProxy(proxy)) {
            return ReflectHelper.getValueByFieldName(proxy, "CGLIB$CALLBACK_0");
        }

        return getProxyTarget(proxy);
    }

    /**
     * 获取SpringAOP代理的目标对象
     * @param proxy 代理对象
     * @return
     * @throws Exception
     */
    public static Object getTarget(Object proxy) throws Exception {
        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }

        if (AopUtils.isJdkDynamicProxy(proxy)) {
            proxy = getJdkDynamicProxyTargetObject(proxy);
        } else if (AopUtils.isCglibProxy(proxy)) { //cglib
            proxy = getCglibProxyTargetObject(proxy);
        }

        return getTarget(proxy);
    }

    private static Object getCglibProxyTargetObject(Object proxy)
        throws Exception {
        Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
        h.setAccessible(true);

        Object dynamicAdvisedInterceptor = h.get(proxy);

        Field advised = dynamicAdvisedInterceptor.getClass()
                                                 .getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised
                         .get(dynamicAdvisedInterceptor)).getTargetSource()
                         .getTarget();

        return target;
    }

    private static Object getJdkDynamicProxyTargetObject(Object proxy)
        throws Exception {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);

        AopProxy aopProxy = (AopProxy) h.get(proxy);

        Field advised = aopProxy.getClass().getDeclaredField("advised");
        advised.setAccessible(true);

        Object target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource()
                         .getTarget();

        return target;
    }
}
