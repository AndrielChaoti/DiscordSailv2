package Objects;

import java.util.regex.Pattern;

/**
 * Created by Vaerys on 15/01/2017.
 */
public class SplitFirstObject {
    String firstWord;
    String rest;
    public SplitFirstObject(String from){
        String[] splitFrom = from.split(" ");
        if (splitFrom.length != 0 || splitFrom != null){
            firstWord = splitFrom[0];
            rest = from.replaceFirst(Pattern.quote(firstWord + " "),"");
        }
    }

    public String getFirstWord() {
        return firstWord;
    }

    public String getRest() {
        return rest;
    }
}