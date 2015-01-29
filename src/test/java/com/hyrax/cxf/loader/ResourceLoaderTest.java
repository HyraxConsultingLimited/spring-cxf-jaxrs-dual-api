package com.hyrax.cxf.loader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResourceLoaderTest {

    @Mock
    private ApplicationContext mockApplicationContext;

    private ResourceLoader resourceLoader;

    @Before
    public void setUp() throws Exception {
        this.resourceLoader = new ResourceLoader();
    }

    @Test
    public void objectTypeReturnedAListClass() throws Exception {
        assertEquals(this.resourceLoader.getObjectType(), List.class);
    }

    @Test
    public void resourceLoaderIsSingleton() throws Exception {
        assertEquals(this.resourceLoader.isSingleton(), true);
    }

    @Test
    public void classWithPathAnnotationIsAddedToServices() throws Exception {
        Map<String, Object> beansWithAnnotations = new HashMap<String, Object>();
        final ClassWithPathAnnotation expectedService = new ClassWithPathAnnotation();
        beansWithAnnotations.put("key", expectedService);

        when(mockApplicationContext.getBeansWithAnnotation((Service.class))).thenReturn(beansWithAnnotations);

        this.resourceLoader.setApplicationContext(mockApplicationContext);

        assertEquals(this.resourceLoader.getObject().size(), 1);
        assertEquals(this.resourceLoader.getObject().get(0), expectedService);
    }

    @Test
    public void classWithMethodPathAnnotationIsAddedToServices() throws Exception {
        Map<String, Object> beansWithAnnotations = new HashMap<String, Object>();
        final ClassWithMethodPathAnnotation expectedService = new ClassWithMethodPathAnnotation();
        beansWithAnnotations.put("key", expectedService);

        when(mockApplicationContext.getBeansWithAnnotation((Service.class))).thenReturn(beansWithAnnotations);

        this.resourceLoader.setApplicationContext(mockApplicationContext);

        assertEquals(this.resourceLoader.getObject().size(), 1);
        assertEquals(this.resourceLoader.getObject().get(0), expectedService);
    }

    @Test
    public void interfaceWithPathAnnotationIsAddedToServices() throws Exception {
        Map<String, Object> beansWithAnnotations = new HashMap<String, Object>();
        final ClassWithAnnotatedPathInterface expectedService = new ClassWithAnnotatedPathInterface();
        beansWithAnnotations.put("key", expectedService);

        when(mockApplicationContext.getBeansWithAnnotation((Service.class))).thenReturn(beansWithAnnotations);

        this.resourceLoader.setApplicationContext(mockApplicationContext);

        assertEquals(this.resourceLoader.getObject().size(), 1);
        assertEquals(this.resourceLoader.getObject().get(0), expectedService);
    }

    @Test
    public void interfaceWithMethodPathAnnotationIsAddedToServices() throws Exception {
        Map<String, Object> beansWithAnnotations = new HashMap<String, Object>();
        final ClassWithAnnotatedMethodPathInterface expectedService = new ClassWithAnnotatedMethodPathInterface();
        beansWithAnnotations.put("key", expectedService);

        when(mockApplicationContext.getBeansWithAnnotation((Service.class))).thenReturn(beansWithAnnotations);

        this.resourceLoader.setApplicationContext(mockApplicationContext);

        assertEquals(this.resourceLoader.getObject().size(), 1);
        assertEquals(this.resourceLoader.getObject().get(0), expectedService);
    }

    @Path("/")
    private interface ClassWithMethodAnnotation {

    }

    private interface InterfaceWithMethodAnnotation {
        @Path("/")
        public void testMethod();
    }

    @Path("/")
    private class ClassWithPathAnnotation {

    }

    private class ClassWithMethodPathAnnotation {
        @Path("/")
        public void testMethod() {
        }
    }

    private class ClassWithAnnotatedPathInterface implements ClassWithMethodAnnotation {

    }

    private class ClassWithAnnotatedMethodPathInterface implements InterfaceWithMethodAnnotation {
        public void testMethod() {

        }
    }
}