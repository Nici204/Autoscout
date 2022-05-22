package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Verkäufer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading, adding, changing and deleting verkäufer
 */
@Path("verkäufer")
public class VerkäuferService {

    /**
     * reads a list of all verkäufer
     * @return verkäufer as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVerkäufer() {
        List<Verkäufer> verkäuferListList = DataHandler.getInstance().readAllVerkäufer();
        return Response
                .status(200)
                .entity(verkäuferListList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readVerkäufer(
            @QueryParam("uuid") String verkäuferUUID
    ) {
        Verkäufer verkäufer = DataHandler.getInstance().readPVerkäuferByUUID(verkäuferUUID);
        return Response
                .status(200)
                .entity(verkäufer)
                .build();
    }

}