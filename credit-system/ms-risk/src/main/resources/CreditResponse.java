package model;


package client;

import jakarta.ws.rs.*;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RiskClient {

    public String test() {
        return "ok";
    }
}