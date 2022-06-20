package ch.bzz.autoscout.model;

import ch.bzz.autoscout.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.List;

public class Auto {

    @JsonIgnore
    private AutoModell automodell;

    @FormParam("autoUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String autoUUID;

    @FormParam("leistungInPs")
    @NotNull
    @Min(value = 5)
    @Max(value = 1000)
    private int leistungInPs;

    @FormParam("verbrauch")
    @NotEmpty
    @Size(min=2, max=20)
    private String verbrauch;

    @FormParam("kilometer")
    @NotNull
    @Min(value = 5)
    @Max(value = 500000)
    private int kilometer;

    @FormParam("antrieb")
    @NotEmpty
    @Size(min=5, max=20)
    private String antrieb;

    @FormParam("baujahr")
    @NotNull
    @Min(value = 1950)
    @Max(value = 2022)
    private int baujahr;

    public Auto() {
    }

    public Auto(String autoUUID, AutoModell automodell, int leistungInPs, String verbrauch, int kilometer, String antrieb, int baujahr) {
        this.autoUUID = autoUUID;
        this.automodell = automodell;
        this.leistungInPs = leistungInPs;
        this.verbrauch = verbrauch;
        this.kilometer = kilometer;
        this.antrieb = antrieb;
        this.baujahr = baujahr;
    }

    @FormParam("autoModellUUID")
    public void setAutoModellUUID(String autoModellUUID){
        AutoModell autoModell = DataHandler.getInstance().readAutomodellByUUID(autoModellUUID);
        setAutomodell(autoModell);
    }



    public String getAutoUUID() {
        return autoUUID;
    }

    public void setAutoUUID(String autoUUID) {
        this.autoUUID = autoUUID;
    }

    public AutoModell getAutomodell() {
        return automodell;
    }

    public void setAutomodell(AutoModell automodell) {
        this.automodell = automodell;
    }

    public int getLeistungInPs() {
        return leistungInPs;
    }

    public void setLeistungInPs(int leistungInPs) {
        this.leistungInPs = leistungInPs;
    }

    public String getVerbrauch() {
        return verbrauch;
    }

    public void setVerbrauch(String verbrauch) {
        this.verbrauch = verbrauch;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public String getAntrieb() {
        return antrieb;
    }

    public void setAntrieb(String antrieb) {
        this.antrieb = antrieb;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }
}
