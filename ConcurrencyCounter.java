/*
 * Portfolio Project Part II - Java Concurrency Concepts
 * Author: Isaac Bright
 *
 * This program demonstrates two Java threads acting as counters.
 * Thread one counts up from 1 to 20. After thread one finishes,
 * thread two counts down from 20 to 0.
 */

public class ConcurrencyCounter {

    private static final int MAX_COUNT = 20;

    public static void main(String[] args) {
        System.out.println("Java Concurrency Counter Program");
        System.out.println("Thread 1 will count up to " + MAX_COUNT + ".");
        System.out.println("Thread 2 will count down to 0 after Thread 1 completes.\n");

        Thread countUpThread = new Thread(new CountUpTask(MAX_COUNT), "Count-Up Thread");
        Thread countDownThread = new Thread(new CountDownTask(MAX_COUNT), "Count-Down Thread");

        countUpThread.start();

        try {
            countUpThread.join();
            countDownThread.start();
            countDownThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Program interrupted before both counters completed.");
        }

        System.out.println("\nBoth counter threads have completed.");
    }

    private static class CountUpTask implements Runnable {
        private final int limit;

        public CountUpTask(int limit) {
            this.limit = limit;
        }

        @Override
        public void run() {
            for (int i = 1; i <= limit; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }

    private static class CountDownTask implements Runnable {
        private final int start;

        public CountDownTask(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i >= 0; i--) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}
