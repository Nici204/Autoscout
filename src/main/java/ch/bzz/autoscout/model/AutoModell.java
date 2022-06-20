package ch.bzz.autoscout.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class AutoModell {

    @FormParam("autoModellUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String autoModellUUID;

    @FormParam("marke")
    @NotEmpty
    @Size(min=1, max=20)
    private String marke;

    @FormParam("modell")
    @NotEmpty
    @Size(min=1, max=20)
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
