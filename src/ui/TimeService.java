
package ui;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimeService {

    private static final int TIME_LIMIT = 10;

    private static final BlockingQueue<String> inputQueue =
            new LinkedBlockingQueue<>();

    private static boolean readerStarted = false;

    private static synchronized void startInputReader(Scanner sc) {

        if (readerStarted) {
            return;
        }

        readerStarted = true;

        Thread inputThread = new Thread(() -> {
            while (sc.hasNextLine()) {
                try {
                    inputQueue.put(sc.nextLine());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        inputThread.setDaemon(true);
        inputThread.start();
    }

    public static String getAnswerWithTimer(Scanner sc) {

        startInputReader(sc);

        inputQueue.clear();

        AtomicBoolean answered = new AtomicBoolean(false);

        Thread timerThread = new Thread(() -> {

            for (int i = TIME_LIMIT; i >= 1; i--) {

                if (answered.get()) {
                    return;
                }

                System.out.println("Time left: " + i + " seconds");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });

        timerThread.start();

        try {

            String answer = inputQueue.poll(
                    TIME_LIMIT, TimeUnit.SECONDS);

            answered.set(true);
            timerThread.interrupt();

            if (answer == null) {
                System.out.println("Time's up!");
                return null;
            }

            return answer;

        } catch (InterruptedException e) {

            answered.set(true);
            timerThread.interrupt();
            Thread.currentThread().interrupt();

            return null;
        }
    }
}
