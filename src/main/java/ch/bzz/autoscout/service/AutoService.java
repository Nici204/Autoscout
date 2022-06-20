package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Auto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;


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

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertAuto(
            @Valid @BeanParam Auto auto
    ){
        auto.setAutoUUID(UUID.randomUUID().toString());

        DataHandler.getInstance().insertAuto(auto);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateAuto(
            @Valid @BeanParam Auto auto
    ){
        int httpStatus = 200;
        Auto oldAuto = DataHandler.getInstance().readAutoByUUID(auto.getAutoUUID());
        if(oldAuto != null){
            oldAuto.setLeistungInPs(auto.getLeistungInPs());
            oldAuto.setVerbrauch(auto.getVerbrauch());
            oldAuto.setKilometer(auto.getKilometer());
            oldAuto.setAntrieb(auto.getAntrieb());
            oldAuto.setBaujahr(auto.getBaujahr());

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
    public Response deleteAuto(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String autoUUID
    ){
        int httpStatus = 200;
        if(!DataHandler.getInstance().deleteAuto(autoUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();

    }
}
