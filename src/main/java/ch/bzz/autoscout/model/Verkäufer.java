package ch.bzz.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Verkäufer {

    @JsonIgnore
    private List<Auto> autoList;

    private String verkäuferUUID;
    private String name;
    private String adresse;
    private String telefonNr;

    public Verkäufer(String verkäuferUUID, List<Auto> autoList, String name, String adresse, String telefonNr) {
        this.verkäuferUUID = verkäuferUUID;
        this.autoList = autoList;
        this.name = name;
        this.adresse = adresse;
        this.telefonNr = telefonNr;
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
