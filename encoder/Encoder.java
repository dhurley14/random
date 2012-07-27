import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Takes in a text file and encodes it using
 * a caesar cipher.
 * 
 * @author Devin Hurley
 * @version July 27th, 2012
 */
public class Encoder
{
    public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
            'q','r','s','t','u','v','w','x','y','z'};
    private static int inputConstant = 0;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);

        System.out.print("Input file: " );
        String inputFileName = console.next();
        System.out.println();
        System.out.print("Output file: " );
        String outputFileName = console.next();
        System.out.println();
        System.out.println("Enter a number to shift by >>> ");
        inputConstant = console.nextInt();

        // Construct the Scanner and PrintWriter objects for reading and writing

        File inputFile = new File(inputFileName);
        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(outputFileName);
        //PrintWriter charOut = new PrintWriter("yar");

        out.print(""); // clear "out"
        //charOut.print(""); // clear "charOut"

        while(in.hasNextLine()){
            String line = in.nextLine();

            String result = "";
            String saver = "";

            saver += line; // saves the chars at that place.

            result += replacer(line);
            out.println(result);
            //charOut.println(saver);
            System.out.println(saver);
        }
        in.close();
        out.close();
        //charOut.close();
    }

    //method to sort through and find out where in our alphabet the character at that spot lies
    public static String replacer(String str){
        //find where in our alphabet the char is
        // set the substring to equal the char in alphabet
        // plus one;
        String temp = "";
        char myChar;
        char returnedChar = ' ';
        for(int i =0; i<str.length(); i++){
            myChar = str.charAt(i);
            returnedChar = thisCharWithThat(myChar);
            temp += returnedChar;
        }
        return temp;
    }

    public static char thisCharWithThat(char c){
        char toReturn = c;
        if(toReturn == ' '){

        }else{
            for(int my = 0; my<alphabet.length-inputConstant; my++){
                if(toReturn == alphabet[my]){
                    toReturn = alphabet[my+inputConstant];
                    break;
                }
            }
        }
        return toReturn;
    }
}
