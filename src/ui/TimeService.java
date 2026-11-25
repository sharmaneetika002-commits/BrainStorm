package ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

public class TimeService {

    private ScheduledExecutorService executor;
    private ScheduledFuture<?> timerTask;

    private volatile int remainingSeconds;

    public interface TimerListener {
        void onTimeUpdate(int seconds);
        void onTimeUp();
    }

    public TimeService() {
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    public synchronized void startTimer(
            int seconds, TimerListener listener) {

        stopTimer();

        if (seconds <= 0 || listener == null) {
            throw new IllegalArgumentException(
                "Invalid timer settings."
            );
        }

        remainingSeconds = seconds;

        SwingUtilities.invokeLater(
            () -> listener.onTimeUpdate(remainingSeconds)
        );

        timerTask = executor.scheduleAtFixedRate(() -> {

            remainingSeconds--;

            SwingUtilities.invokeLater(
                () -> listener.onTimeUpdate(remainingSeconds)
            );

            if (remainingSeconds <= 0) {

                stopTimer();

                SwingUtilities.invokeLater(
                    listener::onTimeUp
                );
            }

        }, 1, 1, TimeUnit.SECONDS);
    }

    public synchronized void stopTimer() {

        if (timerTask != null) {
            timerTask.cancel(false);
            timerTask = null;
        }
    }

    public synchronized void resetTimer() {
        stopTimer();
        remainingSeconds = 0;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public synchronized void shutdown() {
        stopTimer();
        executor.shutdownNow();
    }
}
 




