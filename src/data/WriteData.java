package data;

import java.io.*;
import java.util.ArrayList;

/**
 * Schrijft een ArrayList weg naar een bestand
 * @author Oscar Wellner
 */
public class WriteData {

    /**
     * Schrijft een ArrayList weg in een object bestand met het datatype <S>
     * @param arrayList de ArrayList om weg te schrijven
     * @param nameOfFile de naam van het bestand waar het object naar wordt geschreven
     * @param <S> het datatype waaruit de objecten in de ArrayList bestaan
     */
    public static <S> void writeToDisk(ArrayList<S> arrayList, String nameOfFile) {
        try {
            FileOutputStream fo = new FileOutputStream(new File(nameOfFile));
            ObjectOutputStream oo = new ObjectOutputStream(fo);
            oo.writeObject(arrayList);
            oo.close();
            fo.close();
        } catch (IOException ex) {
            System.out.println("An error has occurred during writing to "+ nameOfFile +" file");
            System.out.println(ex.toString() + "\n");
        }
    }
}
