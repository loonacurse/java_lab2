//var 20
//task1. Remainder from division ->
//C3 = 2 - StringBuffer
//C17 = 3 - В усіх питальних реченнях заданого тексту знайти та надрукувати без повторень слова заданої довжини. 
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramLab2 {

    public static void main(String[] args) {
        try {
            processText();
        } catch (Exception e) {
            System.out.println("Виникла помилка: " + e.getMessage());
        }
    }

    public static void processText() {
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in, "UTF-8");

            // Крок 1: Введення тексту
            //System.out.println("Введіть текст:");
            //String inputText = scanner.nextLine();
            StringBuffer inputText = new StringBuffer("я люблю небо і зорі? Ти дивишся фільм. Хто це сказав?");


            // Крок 2: Введення довжини слова
            System.out.println("Введіть довжину слова:");
            int wordLength = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            // Обробка тексту
            StringBuffer textBuffer = new StringBuffer(inputText); // Нормалізація пробілів
            HashSet<String> uniqueWords = findWordsInQuestions(textBuffer, wordLength);

            // Виведення результатів
            System.out.println("Слова довжиною " + wordLength + " у питальних реченнях:");
            if (uniqueWords.isEmpty()) {
                System.out.println("Слів заданої довжини не знайдено.");
            } else {
                for (String word : uniqueWords) {
                    System.out.println(word);
                }
            }

        } catch (Exception e) {
            System.out.println("Помилка вводу або обробки тексту: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static HashSet<String> findWordsInQuestions(StringBuffer textBuffer, int wordLength) {
        HashSet<String> uniqueWords = new HashSet<>();

        // Пошук речень, що закінчуються знаком питання
        Pattern sentencePattern = Pattern.compile("[^.!?]*\\?");
        Matcher matcher = sentencePattern.matcher(textBuffer.toString());

        while (matcher.find()) {
            StringBuffer sentenceBuffer = new StringBuffer(matcher.group().trim());

            // Розбиття речення на слова
            String[] words = sentenceBuffer.toString().split("\\s+");

            for (String word : words) {
                StringBuffer wordBuffer = new StringBuffer(word);

                // Видалення небуквених символів
                cleanNonLetters(wordBuffer);

                // Додавання слова, якщо воно не пусте і має задану довжину
                if (wordBuffer.length() == wordLength) {
                    uniqueWords.add(wordBuffer.toString().toLowerCase());
                }
            }
        }
        return uniqueWords;
    }

    public static void cleanNonLetters(StringBuffer wordBuffer) {
        for (int i = 0; i < wordBuffer.length(); i++) {
            char ch = wordBuffer.charAt(i);
            if (!Character.isLetter(ch)) {
                wordBuffer.deleteCharAt(i);
                i--;
            }
        }
    }
}