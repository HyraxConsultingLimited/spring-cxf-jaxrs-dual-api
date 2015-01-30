package com.hyrax.cxf.internal;

import com.hyrax.cxf.shared.SharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/availableInternally")
public class ExampleInternalResource {

    @Autowired
    private SharedService sharedService;

    @GET
    public String exampleInternalResource() {
        return "Im only exposed on the /internal/...API! " + sharedService.getSharedValue();
    }
}