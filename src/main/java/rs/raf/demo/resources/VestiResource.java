package rs.raf.demo.resources;

import rs.raf.demo.entities.Vesti;
import rs.raf.demo.services.VestiService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/news")
public class VestiResource {

    @Inject
    private VestiService vestiService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vesti> all()
    {
        return this.vestiService.allNews();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vesti create(@Valid Vesti vesti) {
        return this.vestiService.addNews(vesti);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vesti find(@PathParam("id") Integer id) {
        return this.vestiService.findNews(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.vestiService.deleteNews(id);
    }

}
