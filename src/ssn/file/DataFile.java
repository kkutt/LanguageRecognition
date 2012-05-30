package ssn.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Klasa reprezentujaca plik zawierajacy zestaw danych (do nauki/testu).
 * 
 * Schemat pliku:
 *     L N
 *     language1 filename1
 *     language2 filename2
 *     ...
 *     languageN filenameN
 * 
 * Gdzie:
 *     L - ilosc analizowanych jezykow
 *     N - ilosc plikow z tekstem
 *     filenameX - sciezka do pliku z tekstem (bez spacji)
 *     languageX - jezyk tekstu w pliku filenameX (liczba z przedzialu [0; L) )
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class DataFile {
    String filename;
    int langQuantity;
    int dataQuantity;
    TextFile textFiles[];

    public DataFile(String filename) {
        this.filename = filename;
        loadData();
    }
    
    private void loadData() {
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            StringTokenizer st = new StringTokenizer(in.readLine());
            langQuantity = Integer.parseInt(st.nextToken());
            dataQuantity = Integer.parseInt(st.nextToken());
            
            textFiles = new TextFile[dataQuantity];
            
            for(int i = 0; i < dataQuantity; i++) {
                //wczytywanie informacji o pliku i
                st = new StringTokenizer(in.readLine());
                int lang = Integer.parseInt(st.nextToken());
                String path = st.nextToken();
                textFiles[i] = new TextFile(path, lang);
            }
        } catch ( FileNotFoundException e ) {
            System.out.println("ERROR: Plik " + filename + " nie istnieje");
            //e.printStackTrace();
            System.exit(1);
        } catch ( IOException e ) {
            System.out.println("ERROR: Blad pliku " + filename);
            //e.printStackTrace();
            System.exit(1);
        }
    }

    public int getDataQuantity() {
        return dataQuantity;
    }

    public int getLangQuantity() {
        return langQuantity;
    }
    
    public TextFile getTextFile(int n) {
        if( n < 0 )
            n = 0;
        if( n >= dataQuantity )
            n = dataQuantity-1;
        
        return textFiles[n];
    }
    
    public int howManyErrors() {
        int err = 0;
        for(int i = 0; i < dataQuantity; i++) {
            if( textFiles[i].hasError() )
                err++;
        }
        return err;
    }
    
}
