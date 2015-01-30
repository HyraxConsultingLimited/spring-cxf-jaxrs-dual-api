package com.hyrax.cxf.external;

import com.hyrax.cxf.shared.SharedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Service
@Path("/availableExternally")
public class ExampleExternalResource {

    @Autowired
    private SharedService sharedService;

    @GET
    public String exampleExternalResource() {
        return "Im only exposed on the /external/...API!" + sharedService.getSharedValue();
    }
}
