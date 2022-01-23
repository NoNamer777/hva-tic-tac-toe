package data;

import java.io.*;
import java.util.ArrayList;

/**
 * haalt een bestand op en geeft dit terug in een ArrayList
 * @author Oscar Wellner
 */
public class ReadData {

    /**
     * Haalt een bestand op en geeft dit terug in een ArrayList
     * @param nameOfFile de naam van het bestand dat gelezen moet worden
     * @param <S> uit dit element bestaat de ArrayList
     * @return de ArrayList<s> opgevult met de informatie van het bestand
     */
    public static <S> ArrayList<S> readDataFile(String nameOfFile) {
        ArrayList<S> arrayList = new ArrayList<>();

        try {
            FileInputStream fi = new FileInputStream(new File(nameOfFile));
            ObjectInputStream oi = new ObjectInputStream(fi);
            arrayList = (ArrayList<S>) oi.readObject();
            oi.close();
            fi.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("An error has occurred during reading from " + nameOfFile + " file");
            System.out.println(ex.toString() + "\n");
        }
        return arrayList;
    }
}
