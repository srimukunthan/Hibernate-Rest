package com.mukunth.main;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/entry")
public class EntryPoint {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
    	System.out.println("ddddd");
    	Company1 company = new Company1("dsfdsf","dddddddddd",232);
        return Response.ok().entity(company).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTest(@Valid Company1 company) {
    	System.out.println(company.toString());
        return Response.created(URI.create("/entry/"+company.getCompanyName())).build();
    }
}