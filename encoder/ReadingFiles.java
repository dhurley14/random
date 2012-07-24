
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
/**
 * This program outputs the number of lines, words,
 * and characters in a file called testfile.txt.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ReadingFiles
{
    public static void main(String[] args) throws FileNotFoundException{
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        //

        // Create an instance of File for testfile.txt file.

        //

        File file = new File("mytext.txt");

       // try {

            // Create a new Scanner object which will read the data 

            // from the file passed in. To check if there are more 
            // line to read from it we check by calling the 

            // scanner.hasNextLine() method. We then read line one 

            // by one till all line is read.

            //
            //String myData = inFile.nextLine();// A
            //StringTokenizer st = new StringTokenizer(myData);//B

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] lines = line.split("\\s");
                char[] characters = line.toCharArray(); 
                //StringTokenizer st = new StringTokenizer(line);
                // while (st.hasMoreTokens()){
                for(int i = 0; i<characters.length; i++){ 
                    //if(!characters[i].equals(" ")){
                    //  charCount++;
                    //}

                }

                //}
                wordCount += lines.length;

                System.out.println(line);
                lineCount++;
            }
            System.out.println(file.toString());
            System.out.println("word count is " + wordCount);
            System.out.println("Character count is " + file.length());
            System.out.println("Number of lines are " + lineCount);

        
        // catch (FileNotFoundException e) {
        // 
        //             System.err.println("Filename not found: " + file);
        //             System.exit(0);
        // 
        //         }
    }

    //Scanner s = new Scanner(new File("testfile.txt"));
}
