import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args){
        
        ArrayList<Integer> numberOfSymbols = new ArrayList<>();
        ArrayList<Integer> lineCounter = new ArrayList<>();
        ArrayList<String> fileList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();
        ArrayList<String> hiraganaList = new ArrayList<>();
        ArrayList<String> splitStringList = new ArrayList<>();
        ArrayList<String> romajiSymbolList = new ArrayList<>();
        ArrayList<String> romajiFromFile = new ArrayList<>();
        ArrayList<Character> japaneseSymbolList = new ArrayList<>();
        ArrayList<Character> outputHiraganaList = new ArrayList<>();
        
        File fileName = null, fileForfList;
        int count = 0;
        String nameOfFile = "H:\\Spring16\\Java\\Lang\\firsttry.txt";
        
        fileForfList = new File(nameOfFile);                  
        if(fileForfList.exists()){
            System.out.println("The file already exists");
        }
        else{
            try{
                fileForfList.createNewFile();
                System.out.println(nameOfFile + " file created");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        JapaneseFunctions jf = new JapaneseFunctions();
            jf.getJSymList(japaneseSymbolList);
            jf.getRoSymList(romajiSymbolList);
            jf.readFileList(fileList, wordList, nameOfFile, lineCounter);
            jf.getRomajiFromFile(wordList, romajiFromFile);
            jf.getUserRomaji(splitStringList, romajiSymbolList, romajiFromFile, numberOfSymbols);
            jf.convertRomajiToHiragana(splitStringList, romajiSymbolList, 
                                       japaneseSymbolList, outputHiraganaList);
            jf.rewriteFileWithHiragana(wordList, nameOfFile, 
                                       outputHiraganaList, numberOfSymbols,
                                       lineCounter);
            //jf.displayHiragana(outputHiraganaList, numberOfSymbols);
            //jf.clearMethods(splitStringList, romajiSymbolList, 
            //                japaneseSymbolList, outputHiraganaList, 
            //                hiraganaList, fileList, wordList);
    }
}
