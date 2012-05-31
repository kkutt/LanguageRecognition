package ssn.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.encog.neural.networks.BasicNetwork;

/**
 * Klasa obslugujaca pliki sieci neuronowej (zapis i odczyt)
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class NetworkFile {
    
    /**
     * Zapisanie sieci network do pliku filename
     */
    public static void saveNetworkToFile(BasicNetwork network, String filename) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        
        try {
            fileOutputStream = new FileOutputStream(filename);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(network);
            objectOutputStream.close();
        } catch(IOException ex) {
            System.out.println("WARN: Blad podczas zapisu sieci do pliku!");
        }
    }
    
    /**
     * Odczytanie sieci z pliku filename.
     * W przypadku bledu zwraca null
     */
    public static BasicNetwork loadNetworkFromFile(String filename) {
        BasicNetwork network = null;
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        
        try{
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            Object loadedFromFile = objectInputStream.readObject();
            network = (BasicNetwork) loadedFromFile;
            objectInputStream.close();
        } catch(IOException e) {
            System.out.println("WARN: Blad podczas wczytywania pliku sieci");
        } catch(ClassNotFoundException e) {
            System.out.println("WARN: Blad podczas ladowania sieci");
        }
        
        return network;
    }
    
}
