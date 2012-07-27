import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Takes in a text file and encodes it using
 * a random character between every character
 * in the text file.
 * 
 * @author Devin Hurley
 * @version July 23rd, 2012
 */
public class Encoder
{
    public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
            'q','r','s','t','u','v','w','x','y','z'};
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        String inputFileName = "mytext.txt";
        String outputFileName = "sendto";
        System.out.print("Input file: " + inputFileName);
        System.out.println();
        System.out.print("Output file: " + outputFileName);
        System.out.println();

        // Construct the Scanner and PrintWriter objects for reading and writing

        File inputFile = new File(inputFileName);
        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(outputFileName);
        PrintWriter charOut = new PrintWriter("yar");

        out.print(""); // clear "out"
        charOut.print(""); // clear "charOut"
        int x = 0;
        while(in.hasNextLine()){
            String line = in.nextLine();

            //String toReturn = myReplace(line);
            String result = "";
            String saver = "";
            for ( int index = 0 ; index < line.length() ; ++index ){ 
                //if(line.charAt(index) == (' ')){
                //    result += 'K';
                // }
                // if(line.charAt(index) == 'i'){
                //   result += '!';
                // }
                // if(line.charAt(index) != ' ' && line.charAt(index) != 'i'){
                //   result += line.substring(index,index+1);
                //}
                if(line.charAt(index) == ' '){
                    result += " ";
                    saver += "K";
                }
                else{
                    result += alphabet[x]; //index - alphabet.length
                    x++;
                    if(x >= alphabet.length){
                        x=0; // initialize x to zero
                    }
                    
                    saver += line.substring(index,index+1); // saves the chars at that place.
                }

            }
            out.println(result);
            charOut.println(saver);
            System.out.println(saver);
        }
        in.close();
        out.close();
        charOut.close();
    }
    
    //method to sort through and find out where in our alphabet the character at that spot lies
    public static String replacer(char c){
        
        return "";
    }
}
