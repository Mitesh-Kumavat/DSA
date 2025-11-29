package String;

public class ReverseWordsInString {

    // APPROACH 1 (Using split())
    /**
     * Reverse words using split().
     *
     * Steps:
     * 1. Split the string by spaces → gives many empty strings also.
     * 2. Traverse from the end and pick only non-empty words.
     * 3. Build the final reversed string.
     *
     * Time Complexity: O(n) // n = length of string
     * Space Complexity: O(n) // split() creates an array of words
     */
    public static String reverseWordsSplit(String s) {

        String[] words = s.split(" ");

        StringBuilder ans = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {

            // ignore empty words
            if (!words[i].strip().equals("")) {
                if (ans.length() > 0)
                    ans.append(" ");
                ans.append(words[i].strip());
            }
        }

        return ans.toString();
    }

    // APPROACH 2 (Optimal – Two Pointer, No extra array)
    /**
     * Optimal method: reverse words by scanning from the end.
     *
     * Steps:
     * 1. Start from end and skip all trailing spaces.
     * 2. Mark the end of a word.
     * 3. Move left until a space is found → this is the start of the word.
     * 4. Extract the word and add it to the answer.
     * 5. Repeat until whole string is processed.
     *
     * Time Complexity: O(n) // scans the string once
     * Space Complexity: O(1) // extra space (ignoring output)
     */
    public static String reverseWords(String s) {

        StringBuilder str = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {

            // skip spaces from the end
            while (i >= 0 && s.charAt(i) == ' ')
                i--;

            if (i < 0)
                break;

            // mark end of the word
            int end = i + 1;

            // move left until space found (start of word)
            while (i >= 0 && s.charAt(i) != ' ')
                i--;

            // extract the word
            String word = s.substring(i + 1, end);

            // add space before appending next word
            if (str.length() > 0)
                str.append(" ");

            str.append(word);
        }

        return str.toString();
    }

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s) + ": ANS");
    }
}
