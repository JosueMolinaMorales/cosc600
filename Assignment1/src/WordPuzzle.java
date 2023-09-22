package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
     * Find the word in the puzzle
     * If a word is found, it prints out where the word is found
     * 
     * @param word the word to find
     */
    public void findWords(String[] words) {
        HashSet<Pair> pairs = new HashSet<Pair>();
        System.out.println(Arrays.toString(words));
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
