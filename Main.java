package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
//  This method counts how many words there are in a string.
//  Input: string
//  Output: int
    public static int countWords(String s){
//      Variable declaration
        int wordCount = 0;
        boolean word = false;
        int endOfLine = s.length() - 1;

//      Start looping through string until length of string is reached
        for (int i = 0; i < s.length(); i++) {
//          Verify if the char is a letter, word = true, because all words start with a character.
//          Will be using Character class(builtin) to identify if next character in string is a letter and not EOL character.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
//
//          if character isn't a letter and there have been letters before(variable word has the value true,
//          counter is increased.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
//          The last word of a string, if it doesn't end with a non letter, like , . ? !, etc
//          It should not count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }

    //    Method for reducing text in string.
//    Input: string with text and integer with the maximum words the new text should contain
//    Output: reduced string
    public static String reducedText(String s, int maxWords){
//         Declaring variables
        int spaceCount = 0;
        boolean word = false;
        int endOfLine = s.length() - 1;
//         Maximum size string array for new string
        String[] stringSplitted = new String[maxWords];
//         This will give the character count in the string to be split
        int stringLength=0;
        int lastIndex=0;

//         Start looping through string
        for(int i = 0; i < s.length(); i++){
//            Verify if the character is a space, if yes then count the words
            if(s.charAt(i) == ' '){
//             increment the count as you have encountered a word
                spaceCount++;
            }
//            after encountering amount equal to integer maxWords words split the sentence from lastIndex
//            to the integer maxWords word.
            if(spaceCount == maxWords){
                stringSplitted[stringLength++] = s.substring(lastIndex, i);
//               To get the next part of the sentence, set the last index to i
                lastIndex = i;
//              set the number of spaces to zero to starting counting the next words
                spaceCount = 0;
                System.out.println("The following result has been collected: \n ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" + stringSplitted[0] + "\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }
        return stringSplitted[0];
    }

    //    This method changes words in string when the match with the first input arraylist and gets replaced
//    Input: String, Arraylist with words to look for, Arraylist with words that needs to be replaced.
    public static String changeSpeak(String s, ArrayList<String> oldSpeak, ArrayList<String> newSpeak){
//         This method changes old speak into newspeak
//         Start looping through arraylist
        for(int i = 0; i < oldSpeak.size(); i++){
//             (?<!\\S) Negative lookbehind which asserts that the match won't be preceded by a non-space character. i.e. the match must be preceded by start of the line boundary or a space.
//             (?!\s?!\S) Negative lookahead which asserts that the match won't be followed by a non-space character, i.e. the match must be followed by end of the line boundary or space
//             Regex example https://regex101.com/r/qF3tV5/52

//             Oldspeak		Newspeak
//             oorlog		vrede
//             vrijheid		slavernij
//             onwetendheid	kracht
            System.out.println("Looking for oldspeak word: " + oldSpeak.get(i));
            System.out.println("This will be replace by newspeak word: " + newSpeak.get(i));
//             Actual replacing
            s = s.replaceAll("(?<!\\S)" + oldSpeak.get(i) + "(?!\\s?!\\S)", newSpeak.get(i));
            System.out.println("\n" + s);
        }
        return s;
    }

    //    Main program
    public static void main(String[] args) {
//      User input
        Scanner textReader = new Scanner(System.in);
        System.out.println("Voer uw tekst in");
        String textInput = textReader.nextLine();
//      Testinput: "Nunc nulla, pulvinar donec at fringilla amet. Non a ridiculus duis aenean sociis dictum penatibus torquent natoque sit duis imperdiet elit eget lobortis etiam natoque congue facilisi et placerat iaculis maecenas ridiculus malesuada taciti velit vehicula vehicula sociis nisi est duis arcu habitant tincidunt feugiat, maecenas habitant nonummy pede pulvinar parturient etiam taciti integer massa venenatis sem tincidunt. Eget. Pretium bibendum urna euismod malesuada sem facilisi. Sodales cum nisi taciti id tortor cras nisi congue ornare massa purus cubilia dignissim cum, dui. Laoreet blandit aenean porttitor. Est dictum vehicula Nam cum blandit scelerisque vulputate netus amet urna viverra etiam proin Scelerisque fusce iaculis. Pretium pretium natoque lacus vulputate vitae libero velit suspendisse nisi id eu. Ornare mattis risus scelerisque, blandit Habitasse. Cubilia velit sapien viverra mauris hendrerit. Urna egestas suscipit condimentum, nascetur phasellus a platea mollis eu dui condimentum in est Faucibus a. Maecenas integer imperdiet erat cras. Sollicitudin ad congue fringilla malesuada conubia faucibus facilisis id sed egestas. Porttitor torquent accumsan. Eget eget. Pharetra placerat sapien potenti montes morbi magna curabitur iaculis tempus class. Nulla. Sodales ac. Faucibus nunc. Interdum venenatis pede molestie volutpat. Nascetur senectus etiam fermentum feugiat tincidunt ornare. Suspendisse ad consectetuer, laoreet vel metus facilisis malesuada."

//      Big Brother check
        String tempTextInputControl = textInput;
        Boolean alarm = false;
        while (alarm == false) {
//            Java is case sensitive, first we lower everything to lowercase and then check
            String textInputControl = tempTextInputControl.toLowerCase();
            if (textInputControl.contains("big") && textInputControl.contains("brother")) {
                //  Test if Big comes first
                if (textInputControl.indexOf("big") < textInputControl.indexOf("brother")) {
                    String textInputControlCheck = textInputControl.substring(textInputControl.indexOf("big")); // Remove everything before Big
                    textInputControlCheck = textInputControlCheck.substring(0, (textInputControlCheck.lastIndexOf("brother") + 7)); // Remove everything after Brother
                    if (countWords(textInputControlCheck) <= 5) { // Less or 3 words between triggers ALARM
                        System.out.println("ALARM, STOPPING THE PROGRAM AND REMOVING ALL OF THE INPUT");
                        alarm = true;
                        Main.main(args);
                    } else { // Check failed, update textInputControl
                        textInputControl = textInputControl.substring((textInputControl.indexOf("Brother")));
                    }
                }
                //  If Brother comes first
                else {
                    String textInputControlCheck = textInputControl.substring(textInputControl.indexOf("brother")); // Remove everything before Brother
                    textInputControlCheck = textInputControlCheck.substring(0, (textInputControlCheck.lastIndexOf("big") + 3)); // Remove everything after Big
                    if (countWords(textInputControlCheck) <= 5) { // Less or 3 words between triggers ALARM
                        System.out.println("ALARM, STOPPING THE PROGRAM AND REMOVING ALL OF THE INPUT");
                        alarm = true;
                        Main.main(args);
                    } else { // Check failed, update textInputControl
                        textInputControl = textInputControl.substring((textInputControl.indexOf("big")));
                    }
                }
            }
            else {
                break;
            }
            textReader.close();
        }

//      First check has been completed, now it's time to start counting words.
//      Using method countWords to count words
        int numberOfWords = countWords(textInput);
        System.out.println("String contains: " + numberOfWords + " words");

        if(numberOfWords > 100){
            System.out.println("String contains too many words, it must be reduced");
            textInput = reducedText(textInput,100);
            textInput = textInput.substring(0, (textInput.lastIndexOf(".") + 1)); // Remove everything after the last dot.
            System.out.println("Reduced string: " + textInput);
            numberOfWords = countWords(textInput);
            System.out.println("This new string contains: " + numberOfWords + " words");
        }

//        Define ArrayLists with words to look for and words that need to be replaced
        ArrayList<String> oldSpeak = new ArrayList<String>(Arrays.asList( "oorlog", "vrijheid", "onwetendheid") );
        ArrayList<String> newSpeak = new ArrayList<String>(Arrays.asList( "vrede", "slavernij", "kracht") );
//        Calling method changeSpeak
        String finalText = changeSpeak(textInput,oldSpeak,newSpeak);
//        Outputting final text in readable format, every sentence with a . gets a new line
        System.out.println("\nThe final text is: \n" + finalText.replace(".",".\n"));
        System.out.println("done!");
    }
}
