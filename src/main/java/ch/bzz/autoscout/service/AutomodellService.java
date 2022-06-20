package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Auto;
import ch.bzz.autoscout.model.AutoModell;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing and deleting automodells
 */
@Path("automodell")
public class AutomodellService {

    /**
     * reads a list of all automodells
     * @return automodells as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAutomodells() {
        List<AutoModell> automodellList = DataHandler.getInstance().readAllAutoModell();
        return Response
                .status(200)
                .entity(automodellList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readAutomodell(
            @QueryParam("uuid") String automodellUUID
    ) {
        AutoModell autoModell = DataHandler.getInstance().readAutomodellByUUID(automodellUUID);
        return Response
                .status(200)
                .entity(autoModell)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAutoModell(
            @Valid @BeanParam AutoModell autoModell

    ){
        autoModell.setAutoModellUUID(UUID.randomUUID().toString());

        DataHandler.getInstance().insertAutomodell(autoModell);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAutoModell(
            @Valid @BeanParam AutoModell autoModell
    ){
        int httpStatus = 200;
        AutoModell oldAutomodell = DataHandler.getInstance().readAutomodellByUUID(autoModell.getAutoModellUUID());
        if(oldAutomodell != null){
            oldAutomodell.setMarke(autoModell.getMarke());
            oldAutomodell.setModell(autoModell.getModell());

            DataHandler.getInstance().updateAuto();
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
    public Response deleteAutoModell(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String autoModellUUID
    ){
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteAutomodell(autoModellUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
