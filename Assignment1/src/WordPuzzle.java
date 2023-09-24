package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class WordPuzzle {
    private ArrayList<ArrayList<String>> puzzle = new ArrayList<>();

    private class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Pair && this.x == ((Pair) other).x && this.y == ((Pair) other).y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }

    /**
     * Constructor for WordPuzzle class.
     * Reads the puzzleinput file and stores it in the puzzle array.
     * 
     * @throws FileNotFoundException if the puzzleinput file is not found.
     */
    public WordPuzzle() {
        // Read the puzzleinput file and store it
        // in the puzzle array
        try {
            File file = new File("./input/puzzleinput.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] letters = line.split("");
                ArrayList<String> row = new ArrayList<>();
                for (String letter : letters) {
                    row.add(letter);
                }
                puzzle.add(row);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public void printPuzzle() {
        for (ArrayList<String> row : puzzle) {
            for (String letter : row) {
                System.out.print(letter + " ");
            }
            System.out.println();
        }
    }

    /**
     * This method benchmarks the time it takes to find words in a word puzzle.
     * It reads a wordlist txt file, converts the words to uppercase, and times the
     * findWords method.
     * The time taken is then printed in milliseconds.
     *
     * @throws FileNotFoundException if the wordlist txt file is not found
     */
    public void benchmark() {
        // Read the wordlist txt file
        try {
            File file = new File("./input/wordlist.txt");
            Scanner reader = new Scanner(file);
            ArrayList<String> words = new ArrayList<String>();
            while (reader.hasNextLine()) {
                String word = reader.nextLine();
                words.add(word.toUpperCase());
            }
            String[] wds = new String[words.size()];
            wds = words.toArray(wds);
            // Time the findWords method
            long startTime = System.nanoTime();
            findWords(wds);
            long endTime = System.nanoTime();
            // Convert to ms
            long time = (endTime - startTime) / 1_000_000;
            System.out.println("The time to find the words is " + time + " ms");

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Find the word in the puzzle
     * If a word is found, it prints out where the word is found
     * 
     * @param word the word to find
     */
    public void findWords(String[] words) {
        HashSet<Pair> pairs = new HashSet<Pair>();
        for (String word : words) {
            findWord(word, pairs);
        }

        // Print Found Puzzle
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (pairs.contains(new Pair(i, j))) {
                    System.out.print(puzzle.get(i).get(j) + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

    }

    /**
     * Finds the given word in the puzzle and adds the coordinates of the first
     * letter of the word to the given set of pairs.
     * 
     * @param word  the word to be found in the puzzle
     * @param pairs the set of pairs to which the coordinates of the first letter of
     *              the word will be added if found
     */
    private void findWord(String word, HashSet<Pair> pairs) {
        char ch = word.charAt(0);
        for (int i = 0; i < puzzle.size(); i++) {
            for (int j = 0; j < puzzle.get(i).size(); j++) {
                if (puzzle.get(i).get(j).charAt(0) == ch) {
                    String[] w = word.split("");
                    if (horizontalCheck(w, pairs, i, j) || verticalCheck(w, pairs, i, j)
                            || diagonalCheck(w, pairs, i, j)) {
                        pairs.add(new Pair(i, j));
                        return;
                    }
                }
            }
        }
    }

    /**
     * Checks if a given word is present in the puzzle diagonally and returns true
     * if found.
     * If found, adds the pairs of the word's letters to the given hashset.
     * 
     * @param word  the word to search for diagonally
     * @param pairs the hashset to add the pairs of the word's letters to if found
     * @param row   the row index of the first letter of the word
     * @param col   the column index of the first letter of the word
     * @return true if the word is found diagonally, false otherwise
     */
    private boolean diagonalCheck(String[] word, HashSet<Pair> pairs, int row, int col) {
        // check Top Left Diagonal
        boolean found = false;
        int newRow = row - 1;
        int newCol = col - 1;
        int i = 1;
        HashSet<Pair> potPairs = new HashSet<>();
        while (!found && newRow > 0 && newCol > 0) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(newRow).get(newCol).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(newRow, newCol));
            newCol--;
            newRow--;
            i++;
        }

        // Check Top Right Diagonal
        i = 1;
        newRow = row - 1;
        newCol = col + 1;
        while (!found && newRow > 0 && newCol < puzzle.get(row).size()) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(newRow).get(newCol).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(newRow, newCol));
            newRow--;
            newCol++;
            i++;
        }

        // Check Bottom Left Diagonal
        newRow = row + 1;
        newCol = col - 1;
        while (!found && newRow < puzzle.size() && newCol > 0) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(newRow).get(newCol).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(newRow, newCol));
            newCol--;
            newRow++;
            i++;
        }

        // Check Bottom Right Diagonal
        newRow = row + 1;
        newCol = col + 1;
        while (!found && newRow < puzzle.size() && newCol < puzzle.get(newRow).size()) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(newRow).get(newCol).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(newRow, newCol));
            newCol++;
            newRow++;
            i++;
        }

        if (found) {
            // Add pairs to hashset
            pairs.addAll(potPairs);
        }
        return found;
    }

    /**
     * Checks if the given word can be found horizontally in the puzzle starting
     * from the given row and column.
     * 
     * @param word  the word to search for
     * @param pairs a HashSet of Pair objects representing the row and column
     *              indices of each letter in the word found in the puzzle
     * @param row   the row index to start searching from
     * @param col   the column index to start searching from
     * @return true if the word is found horizontally in the puzzle, false otherwise
     */
    private boolean horizontalCheck(String[] word, HashSet<Pair> pairs, int row, int col) {
        // check Left
        boolean found = false;
        int x = col - 1;
        int i = 1;
        HashSet<Pair> potPairs = new HashSet<>();
        while (!found && x > 0) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(row).get(x).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(row, x));
            x--;
            i++;
        }

        // Check Right
        i = 1;
        x = col + 1;
        while (!found && x < puzzle.get(row).size()) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(row).get(x).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(row, x));
            x++;
            i++;
        }

        if (found) {
            // Add pairs to hashset
            pairs.addAll(potPairs);
        }
        return found;
    }

    /**
     * Checks if a given word can be found vertically in the puzzle, starting from
     * the given row and column.
     * 
     * @param word  the word to search for
     * @param pairs a HashSet of Pair objects representing the coordinates of the
     *              letters in the word found in the puzzle
     * @param row   the row to start searching from
     * @param col   the column to search in
     * @return true if the word is found, false otherwise
     */
    private boolean verticalCheck(String[] word, HashSet<Pair> pairs, int row, int col) {
        // check top
        boolean found = false;
        int y = row - 1;
        int i = 1;
        HashSet<Pair> potPairs = new HashSet<>();
        while (!found && y > 0) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(y).get(col).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(y, col));
            y--;
            i++;
        }

        // Check Bottom
        i = 1;
        y = row + 1;
        while (!found && y < puzzle.size()) {
            if (i >= word.length) {
                found = true;
                break;
            }
            if (!puzzle.get(y).get(col).equals(word[i])) {
                potPairs.clear();
                break;
            }
            potPairs.add(new Pair(y, col));
            y++;
            i++;
        }

        if (found) {
            // Add pairs to hashset
            pairs.addAll(potPairs);
        }
        return found;
    }
}
