package ch.bzz.autoscout.data;

import ch.bzz.autoscout.model.Auto;
import ch.bzz.autoscout.model.AutoModell;
import ch.bzz.autoscout.model.Verkäufer;
import ch.bzz.autoscout.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Auto> autoList;
    private List<Verkäufer> verkäuferList;
    private List<AutoModell> autoModellList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler(){
        setAutoList(new ArrayList<>());

    }


    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }

    /**
     * reads all Autos
     * @return list of Autos
     */
    public List<Auto> readAllAutos(){
        return getAutoList();
    }

    /**
     * reads a auto by its uuid
     * @param autoUUID
     * @return the Auto (null=not found)
     */
    public Auto readAutoByUUID(String autoUUID){
        Auto auto = null;
        for(Auto entry : getAutoList()){
            if(entry.getAutoUUID().equals(autoUUID)){
                auto = entry;
            }
        }
        return auto;
    }

    /**
     * reads all Verkäufer
     * @return list of verkäufer
     */
    public List<Verkäufer> readAllVerkäufer() {

        return getVerkäuferList();
    }

    /**
     * reads a verkäufer by its uuid
     * @param verkäuferUUID
     * @return the verkäufer (null=not found)
     */
    public Verkäufer readPVerkäuferByUUID(String verkäuferUUID) {
        Verkäufer verkäufer = null;
        for (Verkäufer entry : getVerkäuferList()) {
            if (entry.getVerkäuferUUID().equals(verkäuferUUID)) {
                verkäufer = entry;
            }
        }
        return verkäufer;
    }

    /**
     * reads all Automodelle
     * @return list of automodelle
     */
    public List<AutoModell> readAllAutoModell() {

        return getAutoModellList();
    }

    /**
     * reads a automodell by its uuid
     * @param autoModellListUUID
     * @return the automodell (null=not found)
     */
    public AutoModell readAutomodellByUUID(String autoModellListUUID) {
        AutoModell autoModell = null;
        for (AutoModell entry : getAutoModellList()) {
            if (entry.getAutomodellUUID().equals(autoModellListUUID)) {
                autoModell = entry;
            }
        }
        return autoModell;
    }

    /**
     * reads the autos from the JSON-File
     */
    private void readAutoJSON(){
        try{
            String path = Config.getProperty("autoJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Auto[] autos = objectMapper.readValue(jsonData, Auto[].class);
            for(Auto auto : autos){
                getAutoList().add(auto);
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the verkäufer from the JSON-File
     */
    private void readVerkäuferJSON(){
        try{
            String path = Config.getProperty("verkäuferJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Verkäufer[] verkäufers = objectMapper.readValue(jsonData, Verkäufer[].class);
            for(Verkäufer verkäufer : verkäufers){
                getVerkäuferList().add(verkäufer);
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the automodelle from the JSON-File
     */
    private void readAutomodellJSON(){
        try{
            String path = Config.getProperty("automodellJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            AutoModell[] autoModells = objectMapper.readValue(jsonData, AutoModell[].class);
            for(AutoModell autoModell : autoModells){
                getAutoModellList().add(autoModell);
            }
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    public List<Auto> getAutoList() {
        return autoList;
    }

    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public List<Verkäufer> getVerkäuferList() {
        return verkäuferList;
    }

    public void setVerkäuferList(List<Verkäufer> verkäuferList) {
        this.verkäuferList = verkäuferList;
    }

    public List<AutoModell> getAutoModellList() {
        return autoModellList;
    }

    public void setAutoModellList(List<AutoModell> autoModellList) {
        this.autoModellList = autoModellList;
    }
}
