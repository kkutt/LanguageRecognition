package ssn;

import java.awt.EventQueue;
import org.encog.Encog;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import ssn.file.DataFile;
import ssn.file.NetworkFile;
import ssn.network.NetworkUtils;
import ssn.gui.Gui;

/**
 * Program do rozpoznawania jezyka zadanego fragmentu tekstu.
 * Wykorzystuje siec neuronowa, ktora rozpoznaje jezyk na podstawie czestotliwosci wystepowania liter we fragmencie
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class LanguageRecognition {

    static final String LEARN_MODE = "L";
    static final String TEST_MODE = "T";
    public static final int LETTERS_COUNT = 26;  //liczba wejsc sieci neuronowej
    static final int INNER_LAYER_SIZE = 10;
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Gui gui = new Gui();
                gui.setVisible(true);
            }
        });
        
        
        String mode = "";
        String dataFileName = "def_data.txt";
        String networkFileName = "def_network.net";
        
        //obsluga argumentow wywolania programu
        if ( args.length <= 0 || args.length > 3 ) {
            System.out.println("ERROR: Niewlasciwa ilosc argumentow!");
            return;
        }
        if ( args.length > 0 ) {
            mode = args[0];
        }
        if ( args.length > 1 ) {
            dataFileName = args[1];
        }
        if ( args.length > 2 ) { 
            networkFileName = args[2];
        }
        
        if( mode.equals(LEARN_MODE) ){
            learn(dataFileName, networkFileName);
        } else if( mode.equals(TEST_MODE) ){
            test(dataFileName, networkFileName);
        } else {
            System.out.println("ERROR: Zly tryb. Dostepne: " + LEARN_MODE + " (nauka) i " + TEST_MODE + " (test).");
        }
        
        Encog.getInstance().shutdown();

    }
    
    /** 
     * tryb: nauka
     */
    private static void learn(String dataFileName, String networkFileName) {
        
        System.out.println("Wybrany tryb: Nauka");
        
        DataFile learnDataFile = new DataFile(dataFileName);
        MLDataSet learnSet = NetworkUtils.prepareDataSet(learnDataFile);
        BasicNetwork network = NetworkUtils.prepareNetwork(LETTERS_COUNT, INNER_LAYER_SIZE, learnDataFile.getLangQuantity());
        
        //nauka sieci neuronowej
        final ResilientPropagation learn = new ResilientPropagation(network, learnSet);
        int epoch = 1;
        do {
            learn.iteration();
            //System.out.println("Epoch #" + epoch + " Error:" + learn.getError());
            epoch++;
        } while(learn.getError() > 0.01);
        System.out.println("Learning iterations: " + epoch + '\n');
        
        //zapisanie sieci do pliku
        NetworkFile.saveNetworkToFile(network, networkFileName);
        
    }
    
    /** 
     * tryb: test
     */
    private static void test(String dataFileName, String networkFileName) {
        
        System.out.println("Wybrany tryb: Test");
        
        DataFile testDataFile = new DataFile(dataFileName);
        MLDataSet testSet = NetworkUtils.prepareDataSet(testDataFile);
        
        BasicNetwork network = NetworkFile.loadNetworkFromFile(networkFileName);
        if(network == null) {
            System.out.println("ERROR: Nie udalo sie poprawnie wczytac sieci z pliku!");
            return;
        }
        
        //test sieci
        NetworkUtils.testNetwork(network, testSet);
        
    }
    
}
