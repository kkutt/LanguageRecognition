package ssn.network;

import java.text.NumberFormat;
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
        
        //System.out.println("Wybrany tryb: Nauka");
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
        //System.out.println("Learning iterations: " + epoch + '\n');
        learnInfo += "<p>Iterations number: " + epoch;
        
        
        /*************** obliczenie czestosci wystepowania liter ***************/
        double[][] languagesAndLettersQuantity = new double[langs.length][LanguageRecognition.LETTERS_COUNT];
        for(int i = 0; i < langs.length; i++) {
            double countFilesInThisLanguage = 0.0;
            for(TextFile text : textFiles) {
                if( langs[i].equalsIgnoreCase(text.getLanguage()) ) {
                    countFilesInThisLanguage++;
                    double[] quantity = text.getLettersQuantity();
                    for( int j = 0; j < LanguageRecognition.LETTERS_COUNT; j++)
                        languagesAndLettersQuantity[i][j] += quantity[j];
                }
            }
            if(countFilesInThisLanguage > 1) {
                for( int j = 0; j < LanguageRecognition.LETTERS_COUNT; j++){
                    languagesAndLettersQuantity[i][j] /= (double)countFilesInThisLanguage;
                    languagesAndLettersQuantity[i][j] *= 100.0;
                }
            }
        }
        /* wyswietlenie czestosci */
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        learnInfo += "<p><br /><br /><br /></p><h4>Average letters quantity:</h4>";
        learnInfo += "<table border=\"0\" cellspacing=\"0\" cellpadding=\"5\"><tr><th></th>";
        for(int i = 0; i < LanguageRecognition.LETTERS_COUNT; i++)
            learnInfo += "<th>" + (char)(i + 'A') + "</th>";
        learnInfo += "</tr>";
        
        for(int i = 0; i < langs.length; i++) {
            learnInfo += "<tr><th>" + langs[i] + "</th>";
            for( int j = 0; j < LanguageRecognition.LETTERS_COUNT; j++) {
                learnInfo += "<td>" + nf.format(languagesAndLettersQuantity[i][j]) + "%</td>";
            }
            learnInfo += "</tr>";
        }
        learnInfo += "</table>";
        
        
        return network;
        
    }
    
    /** 
     * tryb: test
     */
    public static void test(HashSet<TextFile> textFiles, String[] langs, BasicNetwork network) {
        
        //System.out.println("Wybrany tryb: Test");
        
        MLDataSet testSet = NetworkUtils.prepareDataSet(textFiles, langs);
        
        if(network == null) {
            System.out.println("ERROR: Nie udalo sie poprawnie wczytac sieci z pliku!");
            return;
        }
        
        //test sieci
        NetworkUtils.testNetwork(network, testSet, langs);
        
    }
}
