package rs.raf.demo.resources;

import rs.raf.demo.entities.Vesti;
import rs.raf.demo.services.VestiService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/subjects")
public class VestiResource {

    @Inject
    private VestiService vestiService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vesti> all()
    {
        return this.vestiService.allSubjects();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vesti create(@Valid Vesti vesti) {
        return this.vestiService.addSubject(vesti);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vesti find(@PathParam("id") Integer id) {
        return this.vestiService.findSubject(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestiService.deleteSubject(id);
    }

}
