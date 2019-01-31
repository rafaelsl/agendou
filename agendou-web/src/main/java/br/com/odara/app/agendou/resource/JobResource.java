package br.com.odara.app.agendou.resource;


import br.com.odara.app.agendou.business.JobBusiness;
import br.com.odara.app.agendou.entity.Job;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URI;


@Path("job")
public class JobResource {

    @Inject
    private JobBusiness jobBusiness;

    @GET
    public Response findAll(){

        return Response
                .ok(jobBusiness.findAll())
                .build();
    }


    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){

        return Response
                .ok(jobBusiness.findById(id)
                        .orElseGet(null))
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response save (@FormParam("companyName") @NotBlank String companyName,
                          @FormParam("description") @NotBlank String description,
                          @FormParam("salary") @NotNull Double salary,
                          @FormParam("office") @NotBlank String office){

        Job job = jobBusiness.save( Job.build(companyName, description, new BigDecimal( salary ), office ));

        return Response
                .created(URI.create("job/"+ job.getId()))
                .build();

    }

}
