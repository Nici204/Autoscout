package ch.bzz.autoscout.model;

import ch.bzz.autoscout.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class Verkaeufer {

    @JsonIgnore
    private List<Auto> autoList;

    private String verkäuferUUID;
    private String name;
    private String adresse;
    private String telefonNr;

    public Verkaeufer() {
    }

    public Verkaeufer(String verkäuferUUID, List<Auto> autoList, String name, String adresse, String telefonNr) {
        this.verkäuferUUID = verkäuferUUID;
        this.autoList = autoList;
        this.name = name;
        this.adresse = adresse;
        this.telefonNr = telefonNr;
    }

    public void setAutoUUIDList(ArrayNode autoUUIDList){
        setAutoList(new ArrayList<>());
        for (JsonNode jsonNode : autoUUIDList) {
            String autoUUID = jsonNode.get("autoUUID").textValue();
            getAutoList().add(DataHandler.getInstance().readAutoByUUID(autoUUID));
        }
    }

    public String getVerkäuferUUID() {
        return verkäuferUUID;
    }

    public void setVerkäuferUUID(String verkäuferUUID) {
        this.verkäuferUUID = verkäuferUUID;
    }

    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(String telefonNr) {
        this.telefonNr = telefonNr;
    }
}
