package ssn.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import ssn.LanguageRecognition;

/**
 * Klasa reprezentujaca plik zawierajacy tekst
 * 
 * @author Krzysztof Kutt
 * @author Michal Nowak
 */
public class TextFile {
    String filename;
    int language;
    double lettersQuantity[];
    boolean counted;
    boolean error;

    public TextFile(String filename, int language) {
        this.filename = filename;
        this.language = language;
        counted = false;
        error = false;
        lettersQuantity = new double[LanguageRecognition.LETTERS_COUNT];
        for(int i = 0; i < LanguageRecognition.LETTERS_COUNT; i++)
            lettersQuantity[i] = 0.0;
    }

    public int getLanguage() {
        return language;
    }
    
    public double[] getLettersQuantity() {
        if( counted == false )
            countLettersQuantity();
        return lettersQuantity;
    }

    public boolean hasError() {
        return error;
    }
    
    private void countLettersQuantity(){
        counted = true;
        int lettersCount = 0;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line;
            while( ( line = in.readLine() ) != null ){
                line.toLowerCase();
                for(int i = 0; i < line.length(); i++){
                    //dodajemy tylko znaki z alfabetu lacinskiego, pozostale ignorujemy
                    if( line.charAt(i) >= 'a' && line.charAt(i) <= 'z' ){
                        lettersQuantity[ line.charAt(i) - 'a' ]++;
                        lettersCount++;
                    } 
                }
            }
            for(int i = 0; i < LanguageRecognition.LETTERS_COUNT; i++)
                lettersQuantity[i] /= new Double(lettersCount);
        
        } catch (FileNotFoundException ex) {
            System.out.println("WARN: Plik " + filename + "nie istnieje!");
            error = true;
        } catch (IOException ex) {
            System.out.println("WARN: Problemy z plikiem " + filename);
            error = true;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                //do nothing
            }
        }
    }

}
