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
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        String inputFileName = "mytext.txt";
        String outputFileName = "sendto";
        System.out.print("Input file: " + inputFileName);

        System.out.print("Output file: " + outputFileName);

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
            for ( int index = 0 ; index < line.length() ; ++index ){ 
                if(line.charAt(index) == (' ')){
                    result += 'K';
                }
                if(line.charAt(index) == 'i'){
                    result += '!';
                }
                if(line.charAt(index) != ' ' && line.charAt(index) != 'i'){
                    result += line.substring(index,index+1);
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
}
