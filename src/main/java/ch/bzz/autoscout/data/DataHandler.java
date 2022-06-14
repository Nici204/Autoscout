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
    private static List<Auto> autoList;
    private static List<Verkaeufer> verkäuferList;
    private static List<AutoModell> autoModellList;

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
    public static List<Auto> readAllAutos() {
        return getAutoList();
    }

    /**
     * reads a auto by its uuid
     * @param autoUUID
     * @return the Auto (null=not found)
     */
    public static Auto readAutoByUUID(String autoUUID){
        Auto auto = null;
        for(Auto entry : getAutoList()){
            if(entry.getAutoUUID().equals(autoUUID)){
                auto = entry;
            }
        }
        return auto;
    }

    public static void insertAuto(Auto auto){

        getAutoList().add(auto);
        writeAutoJSON();
    }

    public static void updateAuto(){
        writeAutoJSON();
    }

    public static boolean deleteAuto(String autoUUID){
        Auto auto = readAutoByUUID(autoUUID);
        if(auto != null){
            getAutoList().remove(auto);
            writeAutoJSON();
            return true;
        } else{
            return false;
        }
    }

    /**
     * reads all Verkäufer
     * @return list of verkäufer
     */
    public static List<Verkaeufer> readAllVerkäufer() {

        return getVerkäuferList();
    }

    /**
     * reads a verkäufer by its uuid
     * @param verkäuferUUID
     * @return the verkäufer (null=not found)
     */
    public static Verkaeufer readPVerkäuferByUUID(String verkäuferUUID) {
        Verkaeufer verkäufer = null;
        for (Verkaeufer entry : getVerkäuferList()) {
            if (entry.getVerkäuferUUID().equals(verkäuferUUID)) {
                verkäufer = entry;
            }
        }
        return verkäufer;
    }

    public static void insertVerkäufer(Verkaeufer verkaeufer){
        getVerkäuferList().add(verkaeufer);
        writeVerkaeuferJSON();
    }

    public static void updateVerkaeufer(){
        writeVerkaeuferJSON();
    }

    public static boolean deleteVerkaeufer(String verkaeuferUUID){
        Verkaeufer verkaeufer = readPVerkäuferByUUID(verkaeuferUUID);
        if(verkaeufer != null){
            getVerkäuferList().remove(verkaeufer);
            writeVerkaeuferJSON();
            return true;
        } else{
            return false;
        }
    }

    /**
     * reads all Automodelle
     * @return list of automodelle
     */
    public static List<AutoModell> readAllAutoModell() {

        return getAutoModellList();
    }

    /**
     * reads a automodell by its uuid
     * @param autoModellListUUID
     * @return the automodell (null=not found)
     */
    public static AutoModell readAutomodellByUUID(String autoModellListUUID) {
        AutoModell autoModell = null;
        for (AutoModell entry : getAutoModellList()) {
            if (entry.getAutoModellUUID().equals(autoModellListUUID)) {
                autoModell = entry;
            }
        }
        return autoModell;
    }

    public static void insertAutomodell(AutoModell autoModell){
        getAutoModellList().add(autoModell);
        writeAutomodellJSON();
    }

    public static void updateAutomodell(){
        writeAutomodellJSON();
    }

    public static boolean deleteAutomodell(String automodellUUID){
        AutoModell autoModell = readAutomodellByUUID(automodellUUID);
        if(autoModell != null){
            getAutoModellList().remove(autoModell);
            writeAutomodellJSON();
            return true;
        } else{
            return false;
        }
    }

    /**
     * reads the autos from the JSON-File
     */
    private static void readAutoJSON(){
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
     * writes all Autos
     */
    private static void writeAutoJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String autoPath = Config.getProperty("autoJSON");
        try{
            fileOutputStream = new FileOutputStream(autoPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutoList());
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    /**
     * reads the verkäufer from the JSON-File
     */
    private static void readVerkäuferJSON(){
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
     * writes all Autos
     */
    private static void writeVerkaeuferJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String verkaeuferPath = Config.getProperty("verkaeuferJSON");
        try{
            fileOutputStream = new FileOutputStream(verkaeuferPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getVerkäuferList());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * reads the automodelle from the JSON-File
     */
    private static void readAutomodellJSON(){
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

    /**
     * writes all Autos
     */
    private static void writeAutomodellJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream = null;
        Writer fileWriter;

        String automodellPath = Config.getProperty("automodellJSON");
        try{
            fileOutputStream = new FileOutputStream(automodellPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getAutoModellList());
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static List<Auto> getAutoList() {
       if(autoList == null){
           setAutoList(new ArrayList<>());
           readAutoJSON();
       }
        return autoList;
    }


    public static void setAutoList(List<Auto> autoList) {
        DataHandler.autoList = autoList;
    }

    public static List<Verkaeufer> getVerkäuferList() {
        if(verkäuferList == null){
            setVerkäuferList(new ArrayList<>());
            readVerkäuferJSON();
        }
        return verkäuferList;
    }

    public static void setVerkäuferList(List<Verkaeufer> verkäuferList) {
        DataHandler.verkäuferList = verkäuferList;
    }

    public static List<AutoModell> getAutoModellList() {

        if(autoModellList == null){
            setAutoModellList(new ArrayList<>());
            readAutomodellJSON();
        }
        return autoModellList;
    }

    public static void setAutoModellList(List<AutoModell> autoModellList) {
        DataHandler.autoModellList = autoModellList;
    }
}
