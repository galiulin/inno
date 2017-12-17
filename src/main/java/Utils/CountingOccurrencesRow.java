package Utils;

public class CountingOccurrencesRow {
    public static int checkLine(String review, char[] word) {
        char[] arrReview = review.toCharArray();
        int localCount = 0;
        int j = 0;
        for (int i = 0; i < arrReview.length; i++) {
            if (arrReview[i] == word[j]) {
                j++;
                if (j == word.length) {
                    localCount++;
                    j = 0;
                }
            } else {
                j = 0;
            }
        }
        return localCount;
    }
}
