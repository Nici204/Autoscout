package ch.bzz.autoscout.model;

import ch.bzz.autoscout.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Verkaeufer {

    @JsonIgnore
    private Auto autoList;

    @FormParam("verkaeuferUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String verkaeuferUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min=1, max=20)
    private String name;

    @FormParam("adresse")
    @NotEmpty
    @Size(min=1, max=20)
    private String adresse;

    @FormParam("telefonNr")
    @NotEmpty
    @Size(min=1, max=20)
    private String telefonNr;

    public Verkaeufer() {
    }

    public Verkaeufer(String verkäuferUUID, Auto autoList, String name, String adresse, String telefonNr) {
        this.verkaeuferUUID = verkäuferUUID;
        this.autoList = autoList;
        this.name = name;
        this.adresse = adresse;
        this.telefonNr = telefonNr;
    }

    /**JsonProperty("autoList")
    public void setAutosByUUID(List<String> autoUUIDS){
        setAutos(new ArrayList<>());
        if(String s : autoUUIDS){
            Auto auto = DataHandler.readAutoByUUID(s);
            if(auto == null){
                throw new NullPointerException("Not existing Auto");
            }
            this.autoList.add(auto);
        }
    }**/


public void setAuto(String autoUUID){
    Auto auto = DataHandler.getInstance().readAutoByUUID(autoUUID);
    if(auto == null){
        throw new NullPointerException("Not Existing auto");
    }
    setAuto(auto);
}

    public String getVerkaeuferUUID() {
        return verkaeuferUUID;
    }

    public void setVerkaeuferUUID(String verkaeuferUUID) {
        this.verkaeuferUUID = verkaeuferUUID;
    }

    public Auto getAutoList() {
        return autoList;
    }
    public void setAuto(Auto auto){
        this.autoList = auto;
    }

    public void setAutoList(Auto autoList) {
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
