

/**
 * Takes in a String and encodes it using
 * a caesar cipher.
 * 
 * @author Devin Hurley
 * @version August 2nd, 2012
 */
public class MiniCoder
{
    public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p',
            'q','r','s','t','u','v','w','x','y','z'};
    public static char[] capAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O',
            'P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static int inputConstant = 0;

    //method to sort through and find out where in our alphabet the character at that spot lies
    public static String replacer(String str){
        //find where in our alphabet the char is
        // set the substring to equal the char in alphabet
        // plus one;
        String temp = "";
        char myChar;
        char returnedChar;
        for(int i =0; i<str.length(); i++){
            myChar = str.charAt(i);
            if(Character.getType(myChar) == Character.UPPERCASE_LETTER){
                returnedChar = thisUCharWithThat(myChar);
            }
            else{
                returnedChar = thisCharWithThat(myChar);
            }
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
                    if(inputConstant > alphabet.length - my){
                        int newPlace = inputConstant - my;
                        toReturn = alphabet[newPlace];
                        break;
                    }
                    else{
                        toReturn = alphabet[my+inputConstant];
                        break;
                    }
                }
            }
        }
        return toReturn;
    }

    public static char thisUCharWithThat(char ch){
        char toReturn = ch; 
        for(int d = 0; d<capAlphabet.length-inputConstant; d++){
            if(toReturn == capAlphabet[d]){
                if(inputConstant > capAlphabet.length - d){
                    int newCon = inputConstant - d;
                    toReturn = capAlphabet[newCon];
                    break;
                }
                else{
                    toReturn = capAlphabet[d+inputConstant];
                    break;
                }

            }
        }
        return toReturn;
    }
}