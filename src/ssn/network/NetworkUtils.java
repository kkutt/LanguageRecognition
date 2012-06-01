package ssn.network;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashSet;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import ssn.file.DataFile;
import ssn.file.TextFile;

/**
 * Funkcje pomocnicze, do obslugi sieci neuronowej
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class NetworkUtils {
    
    static String testInfo = "";
    
    public static String getTestInfo(){
        return "<html>" + testInfo + "</html>";
    }
    
    /**
     * Przygotowanie zestawu danych dla sieci
     */
    public static MLDataSet prepareDataSet(HashSet<TextFile> textFiles, String[] langs) {
        double[][] input = NetworkUtils.prepareInput(textFiles);
        double[][] response = NetworkUtils.prepareResponse(textFiles,langs);
        return new BasicMLDataSet(input, response);
    }
    
    
    /**
     * Przygotowanie danych wejsciowych do sieci
     */
    private static double[][] prepareInput(HashSet<TextFile> textFiles){
        int dataQuantity = textFiles.size();
        int errorsQuantity = DataFile.howManyErrors(textFiles);
        double input[][] = new double[dataQuantity-errorsQuantity][26];
        int index = 0;
        
        for(TextFile text : textFiles) {
            if( ! text.hasError() ){
                double quantity[] = text.getLettersQuantity();
                input[index] = quantity;   //wczytanie czestotliwosci dla kazdego z plikow
                index++;
            }
        }
        return input;
    }
    
    
    /**
     * Przygotowanie idealnej odpowiedzi sieci
     */
    private static double[][] prepareResponse(HashSet<TextFile> textFiles, String[] langs){
        int dataQuantity = textFiles.size();
        int langQuantity = langs.length;
        int errorsQuantity = DataFile.howManyErrors(textFiles);
        double response[][] = new double[dataQuantity-errorsQuantity][langQuantity];
        int index = 0;
        
        //czyszczenie tablicy response
        for(int i = 0; i < dataQuantity; i++)
            for(int j = 0; j < langQuantity; j++)
                response[i][j] = 0.0;
        
        for(TextFile text : textFiles) {
            if( ! text.hasError() ){
                String[] langsCopy = Arrays.copyOf(langs, langs.length);
                Arrays.sort(langsCopy);
                response[index][Arrays.binarySearch(langsCopy, text.getLanguage())] = 1.0;   //ustawianie wlasciwego jezyka dla kazdego z plikow
                index++;
            }
        }
        return response;
    }
    
    
    /**
     * Przygotowanie sieci o zadanej liczbie neuronow w kazdej z trzech warstw
     */
    public static BasicNetwork prepareNetwork(int input, int inner, int output) {
        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null,true,input));
        network.addLayer(new BasicLayer(new ActivationSigmoid(),true,inner));
        network.addLayer(new BasicLayer(new ActivationSigmoid(),false,output));
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }
    
    
    /**
     * Test sieci
     */
    public static void testNetwork(BasicNetwork network, MLDataSet testSet, String[] langs) {
        //System.out.println("Test Results:\n");
        testInfo = "<h2>Test Results:</h2><ol>";
        int test = 0;
        int succ = 0;
        int outputSize = network.getOutputCount();
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        
        for(MLDataPair pair : testSet ) {
            test++;
            //System.out.println("=== Test #" + test + " ===");
            testInfo += "<li>Test " + test + " : <ul>"; 
            
            final MLData output = network.compute(pair.getInput());
            int idealRes = 0;
            int networkRes = 0;
            int secondNetworkRes = 0;
            
            double[] idealResponse = pair.getIdeal().getData();
            for(int i = 0; i < outputSize; i++) {
                if( idealResponse[i] > idealResponse[idealRes] ) {   //w przygotowanych zestawach danych dokladnie jeden neuron
                    idealRes = i;				     //wyjsciowy ma wartosc 1.0; pozostale maja wartosc 0.0
                }
            }
            //System.out.println("Ideal response:   " + langs[idealRes]);
            testInfo += "<li>Ideal response: " + langs[idealRes] + "</li>";
            double[] networkResponse = output.getData();
            for(int i = 0; i < outputSize; i++) {
                if( networkResponse[i] > networkResponse[networkRes] ) {   //jako odpowiedz sieci wybieramy neuron, na ktorym
                    networkRes = i;					   //osiagnieta zostala najwieksza wartosc
                }
            }
            if( networkRes == 0 )
                secondNetworkRes = 1;
            for(int i = 0; i < outputSize; i++) {
                if( networkRes == i )
                    continue;       //brzydkie, ale nie mam pomyslu na lepszy algorytm
                if( networkResponse[i] > networkResponse[secondNetworkRes] )
                    secondNetworkRes = i;
            }
            
            
            //System.out.println("Network response: " + langs[networkRes] + " [ prob: " + nf.format(networkResponse[networkRes]) + "]");
            //System.out.println("Second response:  " + langs[secondNetworkRes] + " [ prob: " + nf.format(networkResponse[secondNetworkRes]) + " ]");
            testInfo += "<li>Network response: " + langs[networkRes] + " (<i>Probability: " + nf.format(networkResponse[networkRes]) + "</i>)</li>";
            testInfo += "<li>Second response: " + langs[secondNetworkRes] + " (<i>Probability: " + nf.format(networkResponse[secondNetworkRes]) + "</i>)</li>";
            if( networkRes == idealRes ){
                succ++;
                testInfo += "<li>Result: <font color=\"green\"><b>GOOD!</b></font></li></ul></li>";
            } else {
                testInfo += "<li>Result: <font color=\"red\"><b>BAD...</b></font></li></ul></li>";
            }
                
            
            //System.out.println();
            
        }
        testInfo += "</ol><h3>Effectiveness: " + nf.format((double)succ/(double)test) + "</h3>";
        //System.out.println("Effectiveness: " + nf.format((double)succ/(double)test));
        
    }
}
