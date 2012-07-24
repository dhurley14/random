import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * this is a program that reads in a file of text
 * and writes to a new file the same text, only 
 * with all letters in upper case using 
 * PrintWriter
 * 
 * @author (Devin Hurley and Mike Paff) 
 * @version (March 26, 2012)
 */
public class WritingFiles
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
                    result += "K";
                }
                if(line.charAt(index) != ' '){
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

    //public static String myReplace(String str){
    //    String result = "";
    //    for ( int index = 0 ; index < str.length() ; ++index ){ 
    //if ( index < str.length() - 2 && str.charAt(index) == 'i')
    //{ 
    //    result += "k" ;
    // }
    //else{
    //result += str.charAt(index) ;
    //}
    //        if(index%2==0){
    //            result +=  str.substring(index,index+1) + "!" ;
    //        }
    //    }

    //    return result ;
    //}
}

