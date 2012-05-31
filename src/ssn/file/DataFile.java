package ssn.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Klasa dzialajaca na plikach zawierajacych zestawy danych (do nauki/testu).
 * 
 * Schemat pliku:
 *     N
 *     language1 filename1
 *     language2 filename2
 *     ...
 *     languageN filenameN
 * 
 * Gdzie:
 *     N - ilosc plikow z tekstem
 *     filenameX - sciezka do pliku z tekstem (bez spacji)
 *     languageX - jezyk tekstu w pliku filenameX (String, np PL, EN, DE)
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class DataFile {
    public static void loadData(String filename, HashSet<TextFile> textFiles) {
        try{
            BufferedReader in = new BufferedReader(new FileReader(filename));
            StringTokenizer st = new StringTokenizer(in.readLine());
            int dataQuantity = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i < dataQuantity; i++) {
                //wczytywanie informacji o pliku i
                st = new StringTokenizer(in.readLine());
                String lang = st.nextToken();
                String path = st.nextToken();
                textFiles.add(new TextFile(path, lang));
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

    public static int howManyErrors(HashSet<TextFile> textFiles) {
        int err = 0;
        for( TextFile text : textFiles ) {
            if( text.hasError() )
                err++;
        }
        return err;
    }
    
}
