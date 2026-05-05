package resource;

package com.example.orchestrator.resource;

import com.example.orchestrator.model.*;
import com.example.orchestrator.service.CreditService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/v1/credit-evaluations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreditResource {

    @Inject
    CreditService service;

    @POST
    public CreditResponse evaluate(CreditRequest request) {
        return service.evaluate(request);
    }
}
