package com.company;
// Ik kan het aanpassen
import java.util.Scanner;

public class Main {
//  Word Counter
    public static int countWords(String s){
        int wordCount = 0;
        boolean word = false;
        int endOfLine = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            // if the char is a letter, word = true.
            if (Character.isLetter(s.charAt(i)) && i != endOfLine) {
                word = true;
                // if char isn't a letter and there have been letters before,
                // counter goes up.
            } else if (!Character.isLetter(s.charAt(i)) && word) {
                wordCount++;
                word = false;
                // last word of String; if it doesn't end with a non letter, it
                // wouldn't count without this.
            } else if (Character.isLetter(s.charAt(i)) && i == endOfLine) {
                wordCount++;
            }
        }
        return wordCount;
    }
//  Text Reducer
    public static String reducedText(String s, int maxWords){
        int spaceCount = 0;
        boolean word = false;
        int endOfLine = s.length() - 1;
        String[] stringSplitted = new String[100];  //assuming the sentence has 100 words or less, you can change the value to Integer.MAX_VALUE instead of 10
        int stringLength=0;    //this will give the character count in the string to be split
        int lastIndex=0;
        // String is limited to maxWords
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==' '){   //check whether the character is a space, if yes then count the words
                spaceCount++;// increment the count as you have encountered a word
            }
            if(spaceCount==100){     //after encountering 10 words split the sentence from lastIndex to the 10th word. For the first time lastIndex would be zero that is starting position of the string
                stringSplitted[stringLength++] = s.substring(lastIndex, i);
                lastIndex=i;    // to get the next part of the sentence, set the last index to 10th word
                spaceCount=0;   // set the number of spaces to zero to starting counting the next 10 words
                System.out.println(stringSplitted[0]);
            }
        }
        return stringSplitted[0];
    }
    public static void main(String[] args) {
//      User input
        Scanner textReader = new Scanner(System.in);
        System.out.println("Voer uw tekst in");
        String textInput = textReader.nextLine();

//      Testinput: "Nunc nulla, pulvinar donec at fringilla amet. Non a ridiculus duis aenean sociis dictum penatibus torquent natoque sit duis imperdiet elit eget lobortis etiam natoque congue facilisi et placerat iaculis maecenas ridiculus malesuada taciti velit vehicula vehicula sociis nisi est duis arcu habitant tincidunt feugiat, maecenas habitant nonummy pede pulvinar parturient etiam taciti integer massa venenatis sem tincidunt. Eget. Pretium bibendum urna euismod malesuada sem facilisi. Sodales cum nisi taciti id tortor cras nisi congue ornare massa purus cubilia dignissim cum, dui. Laoreet blandit aenean porttitor. Est dictum vehicula Nam cum blandit scelerisque vulputate netus amet urna viverra etiam proin Scelerisque fusce iaculis. Pretium pretium natoque lacus vulputate vitae libero velit suspendisse nisi id eu. Ornare mattis risus scelerisque, blandit Habitasse. Cubilia velit sapien viverra mauris hendrerit. Urna egestas suscipit condimentum, nascetur phasellus a platea mollis eu dui condimentum in est Faucibus a. Maecenas integer imperdiet erat cras. Sollicitudin ad congue fringilla malesuada conubia faucibus facilisis id sed egestas. Porttitor torquent accumsan. Eget eget. Pharetra placerat sapien potenti montes morbi magna curabitur iaculis tempus class. Nulla. Sodales ac. Faucibus nunc. Interdum venenatis pede molestie volutpat. Nascetur senectus etiam fermentum feugiat tincidunt ornare. Suspendisse ad consectetuer, laoreet vel metus facilisis malesuada."

//      Big Brother check
        String textInputControl = textInput;
        Boolean alarm = false;
        while (alarm == false) {
            if (textInputControl.contains("Big") && textInputControl.contains("Brother")) {
                //  Test if Big comes first
                if (textInputControl.indexOf("Big") < textInputControl.indexOf("Brother")) {
                    String textInputControlCheck = textInputControl.substring(textInputControl.indexOf("Big")); // Remove everything before Big
                    textInputControlCheck = textInputControlCheck.substring(0, (textInputControlCheck.lastIndexOf("Brother") + 7)); // Remove everything after Brother
                    if (countWords(textInputControlCheck) <= 5) { // Less or 3 words between triggers ALARM
                        System.out.println("ALARM, STOP THE PROGRAM");
                        alarm = true;
                    } else { // Check failed, update textInputControl
                        textInputControl = textInputControl.substring((textInputControl.indexOf("Brother")));
                        System.out.println(textInputControl);
                    }
                }
                //  If Brother comes first
                else {
                    String textInputControlCheck = textInputControl.substring(textInputControl.indexOf("Brother")); // Remove everything before Brother
                    textInputControlCheck = textInputControlCheck.substring(0, (textInputControlCheck.lastIndexOf("Big") + 7)); // Remove everything after Big
                    if (countWords(textInputControlCheck) <= 5) { // Less or 3 words between triggers ALARM
                        System.out.println("ALARM, STOP THE PROGRAM");
                        alarm = true;
                    } else { // Check failed, update textInputControl
                        textInputControl = textInputControl.substring((textInputControl.indexOf("Big")));
                        System.out.println(textInputControl);
                    }
                }
            }
            else {
                break;
            }
        }
//      Count words
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
    }
}
