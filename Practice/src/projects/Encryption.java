package projects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encryption {
    
    protected static String caesarCypher(String text, int key){
        
        int i, j;
        String alphabet = "";
        String encryptedAlphabet = "";
        String encryptedText = "";
        
        while(key > 26){
            key -= 26;
        }
        
        while(key < 0){
            key += 26;
        }
        
        if(key == 0 || key == 26){
            return text;
        }
        
        //Store the entire alphabet
        for(i=97;i<123;i++){
            alphabet += (char)i;
        }
        
        //Store the encrypted alphabet
        for(i=97;i<123;i++){
            if(i+key < 123){
                encryptedAlphabet += (char)(i + key);
            }else{
                encryptedAlphabet += (char)(i - 26 + key);
            }
        }
        
        for(i=0;i<text.length();i++){
            
            //Check if a character is not letter
            if(!isLetter(text.charAt(i))){
                    encryptedText = encryptedText + text.charAt(i);
                    continue;
                }
            for(j=0;j<alphabet.length();j++){
                if(text.substring(i, i+1).equalsIgnoreCase(alphabet.substring(j, j+1))){
                    if(text.substring(i, i+1).equals(text.substring(i, i+1).toLowerCase())){
                        encryptedText = encryptedText + encryptedAlphabet.substring(j, j+1);
                    }else{
                        encryptedText = encryptedText + encryptedAlphabet.substring(j, j+1).toUpperCase();
                    }
                    break; 
                }
            }
        }
        
        return encryptedText;
        
    }
    
    protected static String twoKeysCaesarCypher(String text, int key1, int key2){
        
        int i;
        String text1 = "";
        String text2 = "";
        String txt = "";
        
        for(i=0;i<text.length();i++){
            if(i%2 == 0){
                text1 += text.charAt(i);
            }else{
                text2 += text.charAt(i);
            }
        }
        
        text1 = caesarCypher(text1, key1);
        text2 = caesarCypher(text2, key2);
        for(i=0;i<text.length();i++){
            if(i%2 == 0){
                txt += text1.charAt(i/2);
            }else{
                txt += text2.charAt(i/2);
            }
        }
        
        return txt;
    }
    
    protected static String twoKeysDecryption(String text, int key1, int key2){
        
        int dekey1, dekey2;
        
        while(key1 > 26){
            key1 -= 26;
        }
        
        while(key1 < 0){
            key1 += 26;
        }
        
        while(key2 > 26){
            key2 -= 26;
        }
        
        while(key2 < 0){
            key2 += 26;
        }
        
        dekey1 = 26 - key1;
        dekey2 = 26 - key2;
        
        return twoKeysCaesarCypher(text, dekey1, dekey2);
    }
    
    protected static String mostCommonWord(String fileName){
        
        int i, idx, max = 0;
        String line, word = "";
        List<String> words = new ArrayList();
        List<Integer> counts = new ArrayList();
        List<String> lines;
        
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while(line != null){
                lines = stringToWords(line);
                for(i=0;i<lines.size();i++){
                    idx = checkArray(words, lines.get(i));
                    if(idx == -1){
                        words.add(lines.get(i));
                        counts.add(1);
                    }else{
                        counts.set(idx, counts.get(idx)+1);
                    }
                }
                line = br.readLine();
            }
            if(!words.isEmpty()){
                for(i=0;i<counts.size();i++){
                    if(max < counts.get(i)){
                        max = counts.get(i);
                        word = words.get(i);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        return word;
        
    }
    
    //Check if a word is already in a given list
    private static int checkArray(List<String> list, String word){
        
        int i;
        
        if(list.isEmpty()){
            return -1;
        }
        
        for(i=0;i<list.size();i++){
            if(list.get(i).equalsIgnoreCase(word)){
                break;
            }
            if(i == list.size()-1){
                i = -1;
                break;
            }
        }
        
        return i;
        
    }
    
    //Separate a string into words
    private static List<String> stringToWords(String text){
        
        int i=0, j=0;
        List<String> words = new ArrayList();
        
        while(i < text.length()){
            
            if(i>0 && !isLetter(text.charAt(i))){
                words.add(text.substring(j, i));
            }
            
            while(i < text.length()-1 && !isLetter(text.charAt(i))){
                if(isLetter(text.charAt(i+1)) && !isLetter(text.charAt(i))){
                    j = i+1;
                    break;
                }else{
                i++;
                }
            }
            
            i++;
            
            if(i == text.length() && isLetter(text.charAt(i-1))){
                words.add(text.substring(j, i));
            }
            
        }
        
        return words;
        
    }
    
    //Check if a given character is a letter
    private static boolean isLetter(char ch){
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }
    
}
