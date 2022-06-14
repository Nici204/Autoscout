package ch.bzz.autoscout.data;

import ch.bzz.autoscout.model.Auto;
import ch.bzz.autoscout.model.AutoModell;
import ch.bzz.autoscout.model.Verkaeufer;
import ch.bzz.autoscout.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private List<Verkaeufer> verkäuferList;
    private List<AutoModell> autoModellList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler(){
        setAutoList(new ArrayList<>());
        readAutoJSON();
        setVerkäuferList(new ArrayList<>());
        readVerkäuferJSON();
        setAutoModellList(new ArrayList<>());
        readAutomodellJSON();
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
    /**public List<Auto> readAllAutos(){
        return getAutoList();
    }**/

    /**
     * reads a auto by its uuid
     * @param autoUUID
     * @return the Auto (null=not found)
     */
    /**public Auto readAutoByUUID(String autoUUID){
        Auto auto = null;
        for(Auto entry : getAutoList()){
            if(entry.getAutoUUID().equals(autoUUID)){
                auto = entry;
            }
        }
        return auto;
    }**/

    /**
     * reads all Verkäufer
     * @return list of verkäufer
     */
    public List<Verkaeufer> readAllVerkäufer() {

        return getVerkäuferList();
    }

    /**
     * reads a verkäufer by its uuid
     * @param verkäuferUUID
     * @return the verkäufer (null=not found)
     */
    public Verkaeufer readPVerkäuferByUUID(String verkäuferUUID) {
        Verkaeufer verkäufer = null;
        for (Verkaeufer entry : getVerkäuferList()) {
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
            if (entry.getAutoModellUUID().equals(autoModellListUUID)) {
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
                //getAutoList().add(auto);
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
            String path = Config.getProperty("verkaeuferJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Verkaeufer[] verkäufers = objectMapper.readValue(jsonData, Verkaeufer[].class);
            for(Verkaeufer verkäufer : verkäufers){
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

    private static void writeAutoJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String autoPath = Config.getProperty("autoJSON");
        try{
            fileOutputStream = new FileOutputStream(autoPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            //objectWriter.writeValue(fileWriter, getAutoList());
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public static void insertAuto(Auto auto){

        //getAutoList().add(auto);
        writeAutoJSON();
    }


    /**public static List<Auto> getAutoList() {
       if(autoList == null){
           setAutoList(new ArrayList<>());
           readAutoJSON();
       }
        return autoList;
    }**/


    public void setAutoList(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public List<Verkaeufer> getVerkäuferList() {
        return verkäuferList;
    }

    public void setVerkäuferList(List<Verkaeufer> verkäuferList) {
        this.verkäuferList = verkäuferList;
    }

    public List<AutoModell> getAutoModellList() {
        return autoModellList;
    }

    public void setAutoModellList(List<AutoModell> autoModellList) {
        this.autoModellList = autoModellList;
    }
}
