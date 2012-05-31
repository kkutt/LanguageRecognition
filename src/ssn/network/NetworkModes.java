package ssn.network;

import java.util.HashSet;
import org.encog.ml.data.MLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import ssn.LanguageRecognition;
import ssn.file.TextFile;

/**
 * Obsluga trybow pracy sieci (aktualnie: nauka i test)
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class NetworkModes {
    
    static String learnInfo = "";
    
    public static String getLearnInfo(){
        return "<html>" + learnInfo + "</html>";
    }    
    
    /** 
     * tryb: nauka
     */
    public static BasicNetwork learn(HashSet<TextFile> textFiles, String[] langs) {
        
        System.out.println("Wybrany tryb: Nauka");
        learnInfo = "<h2>Learning</h2>";
        MLDataSet learnSet = NetworkUtils.prepareDataSet(textFiles, langs);
        BasicNetwork network = NetworkUtils.prepareNetwork(LanguageRecognition.LETTERS_COUNT,
                LanguageRecognition.INNER_LAYER_SIZE, langs.length);
        
        //nauka sieci neuronowej
        final ResilientPropagation learn = new ResilientPropagation(network, learnSet);
        int epoch = 1;
        do {
            learn.iteration();
            //System.out.println("Epoch #" + epoch + " Error:" + learn.getError());
            epoch++;
        } while(learn.getError() > 0.01);
        System.out.println("Learning iterations: " + epoch + '\n');
        learnInfo += "<p>Iterations number: " + epoch;
        return network;
        
    }
    
    /** 
     * tryb: test
     */
    public static void test(HashSet<TextFile> textFiles, String[] langs, BasicNetwork network) {
        
        System.out.println("Wybrany tryb: Test");
        
        MLDataSet testSet = NetworkUtils.prepareDataSet(textFiles, langs);
        
        if(network == null) {
            System.out.println("ERROR: Nie udalo sie poprawnie wczytac sieci z pliku!");
            return;
        }
        
        //test sieci
        NetworkUtils.testNetwork(network, testSet, langs);
        
    }
}
