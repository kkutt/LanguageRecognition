package ssn.network;

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
                response[index][Arrays.binarySearch(langs, text.getLanguage())] = 1.0;   //ustawianie wlasciwego jezyka dla kazdego z plikow
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
    public static void testNetwork(BasicNetwork network, MLDataSet testSet) {
        System.out.println("Test Results:\n");
        int test = 0;
        int succ = 0;
        int outputSize = network.getOutputCount();
        
        for(MLDataPair pair : testSet ) {
            test++;
            System.out.println("=== Test #" + test + " ===");
            
            final MLData output = network.compute(pair.getInput());
            int idealRes = 0;
            int networkRes = 0;
            
            double[] idealResponse = pair.getIdeal().getData();
            for(int i = 0; i < outputSize; i++) {
                if( idealResponse[i] > idealResponse[idealRes] ) {   //w przygotowanych zestawach danych dokladnie jeden neuron
                    idealRes = i;				     //wyjsciowy ma wartosc 1.0; pozostale maja wartosc 0.0
                }
            }
            System.out.println("Ideal response:   " + idealRes);
            
            double[] networkResponse = output.getData();
            for(int i = 0; i < outputSize; i++) {
                if( networkResponse[i] > networkResponse[networkRes] ) {   //jako odpowiedz sieci wybieramy neuron, na ktorym
                    networkRes = i;					   //osiagnieta zostala najwieksza wartosc
                }
            }
            System.out.println("Network response: " + networkRes + " [ prob: " + networkResponse[networkRes] + "]");
            
            if( networkRes == idealRes )
                succ++;
            
            System.out.println();
        }
        
        System.out.println("Effectiveness: " + (double)succ/(double)test);
        
    }
}
