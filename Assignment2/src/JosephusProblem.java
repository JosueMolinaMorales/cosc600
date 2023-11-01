
public class JosephusProblem {

    private static class Queue {
        private int[] arr;
        private int front;
        private int rear;
        private int size;

        public Queue(int capacity) {
            arr = new int[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        /**
         * Add an item to the queue
         * 
         * @param item The item to add to the queue
         */
        public void enqueue(int item) {
            if (isFull()) {
                throw new RuntimeException("Queue is full");
            }

            rear = (rear + 1) % arr.length;
            arr[rear] = item;
            size++;
        }

        /**
         * Remove an item from the queue
         * 
         * @return The item removed from the queue
         */
        public int dequeue() {
            if (isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }

            int item = arr[front];
            front = (front + 1) % arr.length;
            size--;
            return item;
        }

        /**
         * Get the item at the front of the queue
         * 
         * @return The item at the front of the queue
         */
        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("Queue is empty");
            }

            return arr[front];
        }

        /**
         * Get the size of the queue
         * 
         * @return The size of the queue
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Check if the queue is full
         * 
         * @return True if the queue is full, false otherwise
         */
        public boolean isFull() {
            return size == arr.length;
        }
    }

    public static void run() {
        try {
            System.out.println("How many soliders are there?");
            System.out.print(">>> ");
            int n = Integer.parseInt(Solution.in.nextLine());

            // Get name of soliders
            String[] soliders = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter name of solider " + (i + 1) + ": ");
                soliders[i] = Solution.in.nextLine();
            }

            // Get elimination position
            System.out.print("Enter elimination position: ");
            int k = Integer.parseInt(Solution.in.nextLine());

            // Eliminate soliders using queue
            Queue queue = new Queue(n);

            // Add soliders to queue
            for (int i = 0; i < n; i++) {
                queue.enqueue(i);
            }

            // Eliminate soliders
            System.out.println("Eliminating order: ");
            int count = 1;
            while (queue.size > 1) {
                // Move soliders to the back of the queue
                for (int i = 0; i < k - 1; i++) {
                    queue.enqueue(queue.dequeue());
                }
                // Soldier at the front of the queue is eliminated
                int eliminatedSolider = queue.dequeue();
                System.out.println(count + ". " + soliders[eliminatedSolider]);
                count++;
            }

            // Print the last solider standing
            System.out.println(
                    "Last solider standing: " + soliders[queue.peek()] + " who is at position " + (queue.peek() + 1));

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}