import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordSearch {

    private static char[][] wordSearch;
    private static char[][] wordSearchSolutions; // will store the solutions to the word search
    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static ArrayList<String> words = new ArrayList<String>();
    private int arraySize;

    public void printIntro() {
        System.out.println("Welcome to my Word Search Generator!");
        System.out.println("This program allows you to generate your own word search puzzle.");
        manageMenu();
        /*
        - Will print out the introductory lines
        - No returns
        - No parameters
         */
    }
    public char getOption() {
        System.out.println("\nPlease select an option: ");
        System.out.println("Generate a new word search (g) ");
        System.out.println("Print out your word search (p) ");
        System.out.println("Show the solution to your word search (s) ");
        System.out.println("Quit the program (q) ");
        Scanner input = new Scanner(System.in);

        return input.next().toUpperCase().charAt(0);
        /*
        - Prints out the menu and gets option
        - Returns char for the option that is selected
        - No parameters
         */
    }


    public void manageMenu() {
        boolean loop = true;
        while (true) {
            if (loop == false) {
                break;
            }
            char option = getOption();
            switch (option) {
                case 'G':
                    clearWordBank();
                    getWords();
                    setSize();
                    placeWords();
                    fillBlanks();
                    break;
                case 'P':
                    getWordSearch();
                    break;
                case 'S':
                    getSolution();
                    break;
                case 'Q':
                    loop = false;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
         /*
        - Manages the menu, calls methods for command selected
        - No returns
        - No parameters
         */
    }

    public void getWords() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input words to be placed into your word search. Each word" +
                " must be entered on it's own separate line. To stop entering words, type 'Quit': ");
        String word = scan.next();
        while (true) {
            words.add(word.toUpperCase());
            word = scan.next();

            if (word.toUpperCase().charAt(0) == 'Q') {
                break;
            }
        }
        /*
        - Will get all the words to be added into word search
        - No returns
        - No parameters
        */
    }

    public void setSize() {
        arraySize = 0;
        for (String word : words) {
            if (arraySize < word.length()) {
                arraySize = word.length();
            }
        }
        arraySize *= 2;
        wordSearch = new char[arraySize][arraySize];
        wordSearchSolutions = new char[arraySize][arraySize];
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                wordSearch[i][j] = '\0';
                wordSearchSolutions[i][j] = '\0';
            }
        }
        /*
        - Will set the array size to double the length of the longest word entered, also clears word search
        - No returns
        - No parameters
        */
    }

    public static void clearWordBank() {
        words.clear();
        /*
        - Clears the words list
        - No parameters
        - No returns
         */
    }

    public void getWordSearch() {
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                System.out.print(wordSearch[i][j] + " ");
            }
            System.out.println();
        }
        int count = 0;
        System.out.println("\nWord Bank: ");
        for (String word : words) {
            System.out.print(word + "  ");
            count ++;
            if (count == 2) {
                count = 0;
                System.out.println();
            }
        }
        /*
        - Will print out the word search
        - No returns
        - No parameters
         */
    }

    public void getSolution() {
        for (int i = 0; i < wordSearchSolutions.length; i++) {
            for (int j = 0; j < wordSearchSolutions[i].length; j++) {
                System.out.print(wordSearchSolutions[i][j] + " ");
            }
            System.out.println();
        }
         /*
        - Will print out the solution to the word search
        - No returns
        - No parameters
         */
    }

    public void placeWords() {
        for (String word : words) {
            Random random = new Random();
            int direction = random.nextInt(2);
            if (direction == 0) {
                placeHorizontal(word);
            }
            if (direction == 1) {
                placeVertical(word);
            }
        }
        /*
        - Will decide whether to place the words horizontally or vertically
        - No parameters
        - No returns
         */
    }

    public void placeHorizontal(String word) {
        Random random = new Random();
        int placementCount = 0;
        int randomX = random.nextInt(arraySize - word.length());
        int randomY = random.nextInt(arraySize - word.length());
        while (true) {
            boolean isValid = isValidPlacement(randomX, randomY, "horizontal", word);
            if (!isValid) {
                randomX = random.nextInt(arraySize - word.length());
                randomY = random.nextInt(arraySize - word.length());
                placementCount++;
            } else {
                break;
            }
            if (placementCount > 100) {
                break;
            }
        }
        if (placementCount < 100) {
            for (int i = 0; i < word.length(); i++) {
                wordSearch[randomY][randomX + i] = word.charAt(i);
                wordSearchSolutions[randomY][randomX + i] = word.charAt(i);
            }
        }
        /*
        - Places word horizontally
        - A word from list is inputted in
        - No returns
         */
    }

        public void placeVertical(String word) {
            Random random = new Random();

            int placementCount = 0;
            int randomX = random.nextInt(arraySize - word.length());
            int randomY = random.nextInt(arraySize - word.length());
            while (true) {
                boolean isValid = isValidPlacement(randomX, randomY, "vertical", word);
                if (!isValid) {
                    randomX = random.nextInt(arraySize - word.length());
                    randomY = random.nextInt(arraySize - word.length());
                    placementCount++;
                } else {
                    break;
                }
                if (placementCount > 100) {
                    break;
                }
            }
            if (placementCount < 100) {
                for (int i = 0; i < word.length(); i++) {
                    wordSearch[randomY + i][randomX] = word.charAt(i);
                    wordSearchSolutions[randomY + i][randomX] = word.charAt(i);
                }
            }
            /*
        - Places word vertically
        - A word from list is inputted in
        - No returns
         */
        }
    public static boolean isValidPlacement(int x, int y, String placement, String word) {
        if (placement.equals("horizontal")) {
            for (int i = 0; i < word.length(); i++) {
                if (wordSearch[y][x + i] != '\0') {
                    return false;
                }
            }
        }
        if (placement.equals("vertical")) {
            for (int i = 0; i < word.length(); i++) {
                if (wordSearch[y + i][x] != '\0') {
                    return false;
                }
            }
        }
        return true;
        /*
        - Checks if that placement is empty or not
        - Parameters - x & y value that were randomly generated, how the word is being placed, the word
        - Returns a boolean value determining if the placement works or not
         */
    }
    public void fillBlanks() {
        Random rand = new Random();
        for (int i = 0; i < wordSearch.length; i++) {
            for (int j = 0; j < wordSearch[i].length; j++) {
                int random = rand.nextInt(26);
                if (wordSearch[i][j] == '\0') {
                    wordSearch[i][j] = alphabet[random];
                }
            }
        }

        for (int i = 0; i < wordSearchSolutions.length; i++) { // fill the word search solutions array with blanks
            for (int j = 0; j < wordSearchSolutions[i].length; j++) {
                int random = rand.nextInt(26);
                if (wordSearchSolutions[i][j] == '\0') {
                    wordSearchSolutions[i][j] = ' ';
                }
            }
        }
        /*
        - Will fill in the blanks of the array with random letter (or blank spaces for solution)
        - No returns
        - No parameters
         */
    }

}


