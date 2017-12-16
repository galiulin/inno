package Utils;

public class CountingOccurrencesRow {
    public static int checkLine(String review, char[] word) {
        char[] arrReview = review.toCharArray();
        int localCount = 0;
        int j = 0;
//        if (arrReview.length < word.length) return 0; /*если длина проверяемого слова меньше длины слова то выходим*/ //временно убрал т.к. есть проверка
        //в предыдущем уровне стека вызова данной функции и добавил проверку по оставшейся части
        for (int i = 0; i < arrReview.length; i++) {
            if (arrReview[i] == word[j]) {
                j++;
                if (j == word.length) {
                    localCount++;
                    j = 0;
                }
            } else {
                j = 0;
                if (arrReview.length - i < word.length)
                    return 0; /*если длина оставшейся части массива меньше чем длина слова то выходим
                      посчитать среднюю длину слова в текстах для того что-бы понять получаем ли мы преимущество с этой проверкой*/
            }
        }
        return localCount;
    }
}
