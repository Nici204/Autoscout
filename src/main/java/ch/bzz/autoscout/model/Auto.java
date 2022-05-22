package ch.bzz.autoscout.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Auto {

    @JsonIgnore
    private AutoModell automodell;

    private String autoUUID;
    private int leistungInPs;
    private String verbrauch;
    private int kilometer;
    private String antrieb;
    private int baujahr;

    public Auto(String autoUUID, AutoModell automodell, int leistungInPs, String verbrauch, int kilometer, String antrieb, int baujahr) {
        this.autoUUID = autoUUID;
        this.automodell = automodell;
        this.leistungInPs = leistungInPs;
        this.verbrauch = verbrauch;
        this.kilometer = kilometer;
        this.antrieb = antrieb;
        this.baujahr = baujahr;
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
