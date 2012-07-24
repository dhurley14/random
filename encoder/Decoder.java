
/**
 * Will decode text files,
 * however it is currently (July 24th, 2012)
 * being used to test inserting characters
 * between two chars in a string.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Decoder
{
    public static void main(String args[]){
        System.out.println(modifier("abde"));
    }

    public static String modifier(String str){
        String toReturn = "";

        for(int index = 0; index<str.length(); index++){
            toReturn += str.substring(index,index+1) + "z";
        }
       return toReturn;	
    }
}
