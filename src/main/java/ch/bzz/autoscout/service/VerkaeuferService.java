package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Verkaeufer;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing and deleting verkäufer
 */
@Path("verkaeufer")
public class VerkaeuferService {

    /**
     * reads a list of all verkäufer
     * @return verkäufer as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVerkäufer() {
        List<Verkaeufer> verkäuferListList = DataHandler.getInstance().readAllVerkäufer();
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
        Verkaeufer verkäufer = DataHandler.getInstance().readPVerkäuferByUUID(verkäuferUUID);
        return Response
                .status(200)
                .entity(verkäufer)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertVerkaeufer(
            @Valid @BeanParam Verkaeufer verkaeufer
    ){

        verkaeufer.setVerkaeuferUUID(UUID.randomUUID().toString());

        DataHandler.getInstance().insertVerkäufer(verkaeufer);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateVerkaeufer(
            @Valid @BeanParam Verkaeufer verkaeufer,
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("autoUUID") String autoUUID
    ){
        int httpStatus = 200;
        Verkaeufer oldVerkaeufer = DataHandler.getInstance().readPVerkäuferByUUID(verkaeufer.getVerkaeuferUUID());
        if(oldVerkaeufer != null){
            oldVerkaeufer.setName(verkaeufer.getName());
            oldVerkaeufer.setAdresse(verkaeufer.getAdresse());
            oldVerkaeufer.setTelefonNr(verkaeufer.getTelefonNr());
            oldVerkaeufer.setAuto(autoUUID);

            DataHandler.getInstance().updateVerkaeufer();
        }else{
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteVerkaeufer(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String verkaeuferUUID
    ){
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteVerkaeufer(verkaeuferUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}