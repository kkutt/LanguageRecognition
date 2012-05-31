package ssn;

import java.awt.EventQueue;
import org.encog.Encog;
import ssn.gui.Gui;

/**
 * Program do rozpoznawania jezyka zadanego fragmentu tekstu.
 * Wykorzystuje siec neuronowa, ktora rozpoznaje jezyk na podstawie czestotliwosci wystepowania liter we fragmencie
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class LanguageRecognition {

    public static final int LETTERS_COUNT = 26;  //liczba wejsc sieci neuronowej
    public static final int INNER_LAYER_SIZE = 10;
    
    public static final String DEFAULT_DATA_FILENAME = "saved_data";
    public static final String DEFAULT_NETWORK_FILENAME = "saved_net";
    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Gui gui = new Gui();
                gui.setVisible(true);
            }
        });
        
        Encog.getInstance().shutdown();

    }
    
}
