package ch.bzz.autoscout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoModell {

    private String autoModellUUID;
    private String marke;
    private String modell;

    public AutoModell() {
    }

    public AutoModell(String autoModellUUID, String marke, String modell) {
        this.autoModellUUID = autoModellUUID;
        this.marke = marke;
        this.modell = modell;
    }

    public String getAutoModellUUID() {
        return autoModellUUID;
    }

    public void setAutoModellUUID(String autoModellUUID) {
        this.autoModellUUID = autoModellUUID;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }
}
