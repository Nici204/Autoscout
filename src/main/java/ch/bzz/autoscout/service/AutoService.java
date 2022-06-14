package ch.bzz.autoscout.service;

import ch.bzz.autoscout.data.DataHandler;
import ch.bzz.autoscout.model.Auto;

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
            @FormParam("leistungInPs") int leistungInPs,
            @FormParam("verbrauch") String verbrauch,
            @FormParam("kilometer") int kilometer,
            @FormParam("antrieb") String antrieb,
            @FormParam("baujahr") int baujahr,
            @FormParam("autoModellUUID") String automodellUUID

    ){
        Auto auto = new Auto();
        auto.setAutoUUID(UUID.randomUUID().toString());
        setAttributes(
                auto,
                leistungInPs,
                verbrauch,
                kilometer,
                antrieb,
                baujahr,
                automodellUUID
        );

        DataHandler.
    }

    private void setAttributes(
            Auto auto,
            int leistungInPs,
            String verbrauch,
            int kilometer,
            String antrieb,
            int baujahr,
            String automodellUUID
    ){
        auto.setLeistungInPs(leistungInPs);
        auto.setVerbrauch(verbrauch);
        auto.setKilometer(kilometer);
        auto.setAntrieb(antrieb);
        auto.setBaujahr(baujahr);
        auto.setAutoModellUUID(automodellUUID);
    }

}
