import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel.zrna
 */
public class TransliteratorFunctions {
    
    private ArrayList<String> tempStringList = new ArrayList<String>();
    
    public void importHiragana(ArrayList<String> hiraganaList){
   
        String uThirty;
        uThirty = "\\u30";
        String unicode;
        char letter = 'A';
        int digit = 4, counter = 2;
        
        for(int i = 0; i < 69; i++){
            
            if(counter%10 == 0 || counter%11 == 0 || counter%12 == 0 || counter%13 == 0 || counter%14 == 0 || counter%15 == 0){
                unicode = uThirty + String.valueOf(digit) + letter;
                letter++;    
            }
            else{
                if(counter%16 == 0){
                    letter = 'A';
                    digit++;
                    counter = 0;
                }   
                unicode = uThirty + String.valueOf(digit) + counter;
            }            
            counter++;
            hiraganaList.add(unicode);
            System.out.println("\'" + unicode + "\', ");
        }
    }
    
    public void convertRomajiToHiragana(ArrayList<String> splStrList,
                                        ArrayList<String> roSymList,
                                        ArrayList<Character> jSymList,
                                        ArrayList<Character> oHiraList){
        boolean keepLooping = true;
        int ct = 0, outputCount = 0;
        
        while(keepLooping){
            if(splStrList.get(outputCount).equals(roSymList.get(ct))){
                oHiraList.add(jSymList.get(ct));
                outputCount++;
            }
            if(ct == roSymList.size() - 1){
                ct = -1;
            }
            ct++;
            if(outputCount == splStrList.size())
                keepLooping = false;
        }
    }
    
    public void getJSymList(ArrayList<Character> jSymList){
        char[] ch = {'\u3042','\u3044','\u3046','\u3048','\u304A', //(a)
                     '\u304B','\u304D','\u304F','\u3051','\u3053', //(ka)
                     '\u3055','\u3057','\u3059','\u305B','\u305D', //(sa)
                     '\u305F','\u3061','\u3064','\u3066','\u3068', //(ta)
                     '\u306A','\u306B','\u306C','\u306D','\u306E', //(na)
                     '\u306F','\u3072','\u3075','\u3078','\u307B', //(ha)
                     '\u307E','\u307F','\u3080','\u3081','\u3082', //(ma)
                     '\u3084','\u3086','\u3088', // (ya)
                     '\u3089','\u308A','\u308B','\u308C','\u308D', //(ra)
                     '\u308F','\u3090','\u3091','\u3092', // (wa)
                     '\u3093', // (n)
                     '\u304C','\u304E','\u3050','\u3052','\u3054', //(ga)
                     '\u3056','\u3058','\u305A','\u305C','\u305E', //(za)
                     '\u3060','\u3062','\u3065','\u3067','\u3069', //(da)
                     '\u3070','\u3073','\u3076','\u3079','\u307C', //(ba)
                     '\u3071','\u3074','\u3077','\u307A','\u307D', //(pa)
                     '\u0020',
                     '\u3063' //っ
                    };
        for(int i =0; i < ch.length; i++){
            jSymList.add(ch[i]);
        }
    }
    public void getRoSymList(ArrayList<String> roSymList){
        String[] st = {"a","i","u","e","o",
                       "ka","ki","ku","ke","ko",
                       "sa","shi","su","se","so",
                       "ta","chi","tsu","te","to",
                       "na","ni","nu","ne","no",
                       "ha","hi","hu","he","ho",
                       "ma","mi","mu","me","mo",
                       "ya","yu","yo",
                       "ra","ri","ru","re","ro",
                       "wa","wi","we","wo",
                       "n",
                       "ga","gi","gu","ge","go",
                       "za","zi","zu","ze","zo",
                       "da","di","du","de","do",
                       "ba","bi","bu","be","bo",
                       "pa","pi","pu","pe","po",
                       " ", "-"
                      };
        for(int i =0; i < st.length; i++)
            roSymList.add(st[i]);
    }
    
    public void getUserRomaji(ArrayList<String> splStrList, ArrayList<String> roSymList, 
                              ArrayList<String> romFromF, ArrayList<Integer> nos){
        String temp;
        for(int count = 0; count < romFromF.size(); count++){
            char[] charactersOfRomaji = romFromF.get(count).toCharArray();
            int symNumCount = 0;
            
            for(int i = 0; i < charactersOfRomaji.length; i++)
                tempStringList.add(String.valueOf(charactersOfRomaji[i]));

            for(int i = 0; i < tempStringList.size(); i++){
                boolean oneChar = true, twoChar = true, threeChar = true;

                for(int ct = 0; ct < 5; ct++){
                    if(i + 1 < tempStringList.size()){ //so that it can end with an "n"
                       if(tempStringList.get(i).equals(roSymList.get(47)) && tempStringList.get(i+1).equals(roSymList.get(ct))){
                            splStrList.add(roSymList.get(20 + ct));
                            oneChar = twoChar = threeChar = false;
                            i++;
                            symNumCount++;
                        } 
                    }      
                }
                if(oneChar){
                   for(int ct = 0; ct < roSymList.size(); ct++){
                        temp = tempStringList.get(i);
                        if(temp.equals(roSymList.get(ct))){
                            splStrList.add(roSymList.get(ct));
                            twoChar = threeChar = false;
                            symNumCount++;
                        }
                    } 
                }
                if(twoChar){
                    if(tempStringList.get(i).equals(tempStringList.get(i+1))){
                        splStrList.add(roSymList.get(74));
                        threeChar = false;
                        symNumCount++;
                    }
                    else{
                        temp = tempStringList.get(i) + tempStringList.get(i + 1);
                        for(int ct = 0; ct < roSymList.size(); ct++){
                            if(temp.equals(roSymList.get(ct))){
                                splStrList.add(roSymList.get(ct));
                                i++;
                                threeChar = false;
                                symNumCount++;
                            }
                        } 
                    }
                }
                if(threeChar){
                    temp = tempStringList.get(i) + tempStringList.get(i + 1) +
                            tempStringList.get(i + 2);
                    for(int ct = 0; ct < roSymList.size(); ct++){
                        if(temp.equals(roSymList.get(ct))){
                            splStrList.add(roSymList.get(ct));
                            i = i + 2;
                            symNumCount++;
                        }
                    }
                }
            }
            nos.add(symNumCount);
            tempStringList.clear();
        }
    }
    public void displayHiragana(ArrayList<Character> oHiraList, ArrayList<Integer> nos){
        for(int i = 0; i < oHiraList.size(); i++)
            System.out.println(oHiraList.get(i));
        int count = 0;
        for(int ct = 0; ct < nos.size(); ct++){

            String temp = "";
            for(int i = 0; i < nos.get(ct); i++){
                temp += oHiraList.get(count);
                count++;
            }
            JOptionPane.showMessageDialog(null, temp, "Converted Hiragana:", JOptionPane.PLAIN_MESSAGE);
        }        
    }
    public void clearMethods(ArrayList<String> splStrList,
                                        ArrayList<String> roSymList,
                                        ArrayList<Character> jSymList,
                                        ArrayList<Character> oHiraList,
                                        ArrayList<String> hiraganaList,
                                        ArrayList<String> FL, 
                                        ArrayList<String> WL){
        splStrList.clear();
        roSymList.clear();
        jSymList.clear();
        oHiraList.clear();
        FL.clear();
        WL.clear();
    }
    public static void readFileList(ArrayList<String> FL, ArrayList<String> WL, 
                                    String FN, ArrayList<Integer> LC){
        String line = "";
        try{
            BufferedReader input = new BufferedReader(new FileReader(FN));
            if(!input.ready()){
                throw new IOException();
            }
            while ((line = input.readLine()) != null){
                FL.add(line);
            }
            input.close();
        }catch(IOException e ){
            e.printStackTrace();
        }
        
        for(int i = 0; i < FL.size(); i++){
            int lineCounter = 0;
            Scanner sc = new Scanner(FL.get(i));
            while(sc.hasNext()){
                WL.add(sc.next());
                lineCounter++;
            }
            LC.add(lineCounter);                
        }
        //for(int i = 0; i < WL.size(); i++)
            //System.out.println(WL.get(i));
    }
    public static void getRomajiFromFile(ArrayList<String> WL, ArrayList<String> romFromF){
        for(int i = 0; i < WL.size(); i++){
            if(WL.get(i).equals(":"))
                romFromF.add(WL.get(i + 1));
        }
        for(int i = 0; i < romFromF.size(); i++)
            System.out.println(romFromF.get(i));
    }
    public static void rewriteFileWithHiragana(ArrayList<String> WL, String FN,
                                               ArrayList<Character> oHiraList, 
                                               ArrayList<Integer> nos,
                                               ArrayList<Integer> LC){
        try{
            FileWriter fw = new FileWriter(new File(FN));
            Writer out = new BufferedWriter(fw);
            int lineCount = 0, lc = 0, count = 0, ct = 0;
            for(int i = 0; i < WL.size(); i++){
                if(LC.get(lineCount).equals(lc)){
                    out.write("\n");
                    lineCount++;
                    lc = 1;
                }
                lc++;
                out.write(WL.get(i) + " ");
                if(WL.get(i).equals(":")){
                    i++;
                    out.write(WL.get(i) + " ");
                    String temp = "";
                    for(int j = 0; j < nos.get(ct); j++){
                        temp += oHiraList.get(count);
                        count++;
                    }
                    String tempString = "(" + temp + ")";
                    if(i + 1 < WL.size()){
                        if(!((WL.get(i + 1)).equals((tempString))))
                            out.write(tempString);
                    }else
                        out.write(tempString);
                    ct++;
                }
            }                            
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
