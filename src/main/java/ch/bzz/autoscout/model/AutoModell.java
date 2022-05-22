package ch.bzz.autoscout.model;

public class AutoModell {

    private String autoModellUUID;
    private String marke;
    private String modell;

    public AutoModell(String autoModellUUID, String marke, String modell) {
        this.autoModellUUID = autoModellUUID;
        marke = marke;
        modell = modell;
    }

    public String getAutomodellUUID() {
        return autoModellUUID;
    }

    public void setID(String autoModellUUID) {
        this.autoModellUUID = autoModellUUID;
    }

    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        modell = modell;
    }
}
