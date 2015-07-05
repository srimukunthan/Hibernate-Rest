package test;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mukunth.main.Company;
 
@Path("/entry")
public class EntryPoint {
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
    	System.out.println("ggggggggggggggg");
    	Company company = new Company("dsfdsf","dddddddddd",232);
        return Response.ok().entity(company).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTest(@Valid Company company) {
    	System.out.println(company.toString());
        return Response.created(URI.create("/entry/"+company.getCompanyName())).build();
    }
}