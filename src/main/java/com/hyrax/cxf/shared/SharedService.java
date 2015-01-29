package com.hyrax.cxf.shared;

import org.springframework.stereotype.Component;

@Component
public class SharedService {

    public String getSharedValue() {
        return "Im from a shared component!";
    }
}
