package client;

package com.example.orchestrator.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/risk")
@RegisterRestClient(configKey = "risk-api")
public interface RiskClient {

    @GET
    @Path("/score/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    Object getScore(@PathParam("cedula") String cedula);

    @GET
    @Path("/debts/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    Object getDebts(@PathParam("cedula") String cedula);
}