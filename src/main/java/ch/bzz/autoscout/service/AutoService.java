package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Auto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * services for reading, adding, changing and deleting autos
 */
@Path("auto")
public class AutoService {

    /**
     * reads a list of all autos
     * @return autos as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutos() {
       List<Auto> autoList = DataHandler.getInstance().readAllAutos();
       return Response
               .status(200)
               .entity(autoList)
               .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAuto(
            @QueryParam("uuid") String autoUUID
    ) {
        Auto auto = DataHandler.getInstance().readAutoByUUID(autoUUID);
        return Response
                .status(200)
                .entity(auto)
                .build();
    }

}
