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
    private static int inputConstant = 0;
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        String inputFileName = "mytext.txt";
        String outputFileName = "sendto";
        System.out.print("Input file: " + inputFileName);
        System.out.println();
        System.out.print("Output file: " + outputFileName);
        System.out.println();
        System.out.println("Enter a number to shift by >>> ");
        inputConstant = console.nextInt();

        // Construct the Scanner and PrintWriter objects for reading and writing

        File inputFile = new File(inputFileName);
        Scanner in = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(outputFileName);
        PrintWriter charOut = new PrintWriter("yar");

        out.print(""); // clear "out"
        charOut.print(""); // clear "charOut"

        while(in.hasNextLine()){
            String line = in.nextLine();

            //String toReturn = myReplace(line);
            String result = "";
            String saver = "";
            //for ( int index = 0 ; index < line.length() ; ++index ){ 
            //if(line.charAt(index) == (' ')){
            //    result += 'K';
            // }
            // if(line.charAt(index) == 'i'){
            //   result += '!';
            // }
            // if(line.charAt(index) != ' ' && line.charAt(index) != 'i'){
            //   result += line.substring(index,index+1);
            //}

            //else{
            //result += alphabet[x]; //index - alphabet.length
            //x++;
            //if(x >= alphabet.length){
            //    x=0; // initialize x to zero
            //}

            saver += line; // saves the chars at that place.
            //}

            //}
            result += replacer(line);
            out.println(result);
            charOut.println(saver);
            System.out.println(saver);
        }
        in.close();
        out.close();
        charOut.close();
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
            System.out.println("This is the char we are" + 
                " working with " + myChar);
            returnedChar = thisCharWithThat(myChar);
            System.out.println("Now it is this " + returnedChar);
            System.out.println();
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
