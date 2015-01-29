package com.hyrax.cxf.shared;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SharedServiceTest {

    private SharedService sharedService;

    @Before
    public void setUp() throws Exception {
        this.sharedService = new SharedService();
    }

    @Test
    public void sharedComponentReturnsStaticValue() {
        assertEquals(sharedService.getSharedValue(), "Im from a shared component!");
    }

}