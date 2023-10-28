import java.util.ArrayList;
import java.util.Stack;

public class LongestIncSeq {
    private static int[][] directions = {
            { 1, 0 }, // down
            { -1, 0 }, // up
            { 0, 1 }, // right
            { 0, -1 }, // left
            { 1, 1 }, // down right
            { 1, -1 }, // down left
            { -1, 1 }, // up right
            { -1, -1 }// up left
    };

    /**
     * Represents a point in the grid
     */
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {

            if (obj == null) {
                return false;
            }

            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Point)) {
                return false;
            }

            Point other = (Point) obj;

            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            // http://stackoverflow.com/questions/113511/hash-code-implementation
            int result = 17;
            result = 31 * result + x;
            result = 31 * result + y;

            return result;
        }

        public boolean isAdjacent(Point other) {
            int dx = Math.abs(this.x - other.x);
            int dy = Math.abs(this.y - other.y);

            // Points are adjacent if the maximum difference in x and y is 1
            return dx <= 1 && dy <= 1;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    /**
     * Runs the program
     */
    public static void run() {
        // Generate a random grid
        int[][] matrix = generateRandomGrid();
        printGrid(matrix);
        Point[] longestSeq = findLongestIncSeq(matrix);

        System.out.println("Longest increasing sequence: ");
        for (Point p : longestSeq) {
            System.out.println(matrix[p.x][p.y] + " " + p.toString());
        }
        System.out.println("With length: " + longestSeq.length);
    }

    /**
     * Generates a random grid of size 15x15
     */
    public static int[][] generateRandomGrid() {
        int[][] grid = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                grid[i][j] = (int) (Math.random() * 1001);
            }
        }
        return grid;
    }

    /**
     * Finds the longest increasing sequence in the grid using DFS
     * 
     * @param matrix The grid to find the longest increasing sequence in
     * @return An array of points representing the longest increasing sequence
     */
    private static Point[] findLongestIncSeq(int[][] matrix) {
        int longest = 0;
        Point[] longestSeq = null;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // Get the longest path starting from point (i, j)
                ArrayList<Point> path = dfs(matrix, new Point(i, j));

                // If the path is longer than the current longest path, update
                if (path.size() > longest) {
                    longest = path.size();
                    longestSeq = path.toArray(new Point[path.size()]);
                }

                // Clear the path
                path.clear();
            }
        }

        return longestSeq;
    }

    /**
     * Performs a depth first search on the grid starting from the given point to
     * find the longest increasing sequence
     * 
     * @param matrix The grid to search
     * @param start  The starting point
     * @return An array of points representing the longest increasing sequence
     */
    private static ArrayList<Point> dfs(int[][] matrix, Point start) {
        Stack<Point> stack = new Stack<Point>();
        ArrayList<Point> longestPath = new ArrayList<Point>();
        ArrayList<Point> path = new ArrayList<Point>();

        stack.push(start);
        while (!stack.isEmpty()) {
            // Get the next point in the stack
            Point p = stack.pop();
            // Get the last point in the path
            Point last = path.size() > 0 ? path.get(path.size() - 1) : null;

            // Ensure that the point has not already been visited
            if (path.contains(p)) {
                continue;
            }

            // Ensure that the point is adjacent to the last point in the path
            // and that it is greater than the last point
            if (last != null && (!last.isAdjacent(p) || matrix[p.x][p.y] <= matrix[last.x][last.y])) {
                continue;
            }

            // Add the point to the path
            path.add(p);

            // Add all adjacent points to the stack
            int stackSize = stack.size();
            for (int[] dir : directions) {
                int x = p.x + dir[0];
                int y = p.y + dir[1];

                // Ensure that the point is in the grid
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                    continue;
                }

                // Ensure that the point is greater than the current point
                if (matrix[x][y] > matrix[p.x][p.y]) {
                    stack.push(new Point(x, y));
                }
            }

            // If the stack size has not changed, then the point has no adjacent
            // points that are greater than it, so it is the end of the path
            if (stack.size() == stackSize) {
                // If the path is longer than the current longest path, update
                if (path.size() > longestPath.size()) {
                    longestPath = new ArrayList<Point>(path);
                }
                // Remove the point from the path
                path.remove(path.size() - 1);
            }

        }

        return longestPath;
    }

    private static void printGrid(int[][] grid) {
        System.out.println("Grid:");
        for (int[] row : grid) {
            for (int i : row) {
                System.out.printf("%4d", i);
            }
            System.out.println();
        }
    }
}
