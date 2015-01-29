package com.hyrax.cxf.loader;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ResourceLoader implements ApplicationContextAware, FactoryBean<List<?>> {

    private List<Object> services = new LinkedList<Object>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for (Object service : applicationContext.getBeansWithAnnotation(Service.class).values()) {
            if (isJaxRSService(service.getClass())) {
                services.add(service);
            }

            for (Class<?> clazz : service.getClass().getInterfaces()) {
                if (isJaxRSService(clazz)) {
                    services.add(service);
                }
            }
        }
    }

    private boolean isJaxRSService(Class<? extends Object> aClass) {
        if (aClass.getAnnotation(Path.class) != null) {
            return true;
        }

        for (Method method : aClass.getMethods()) {
            if (method.getAnnotation(Path.class) != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<?> getObject() throws Exception {
        return services;
    }

    @Override
    public Class<?> getObjectType() {
        return List.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
