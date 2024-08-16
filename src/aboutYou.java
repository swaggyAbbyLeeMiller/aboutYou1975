package src;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

    public class aboutYou {

        private static final Lock lock = new ReentrantLock();

        public static void animateText(String text, double delay) {
            lock.lock();
            try {
                for (char c : text.toCharArray()) {
                    System.out.print(c);
                    System.out.flush();
                    Thread.sleep((long) (delay * 1000));
                }
                System.out.println();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }

        public static void singLyric(String lyric, double delay, double speed) {
            try {
                Thread.sleep((long) (delay * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            animateText(lyric, speed);
        }

        public static void singSong() {
            String[][] lyrics = {
                    {"Do you think I have forgotten?", "0.1"},
                    {"Do you think I have forgotten?", "0.1"},
                    {"Do you think I have forgotten", "0.1"},
                    {"about you?", "0.2"},
                    {"There was something bout you that now I can't remember", "0.08"},
                    {"It's the same damn thing that made my heart surrender", "0.1"},
                    {"And I miss you on a train, I miss you in the morning", "0.1"},
                    {"I never know what to think about", "0.1"},
                    {"I think about youuuuuuuuuuuuuuuuuuuuuuuuuuu", "0.1"}
            };

            double[] delays = {0.3, 5.0, 10.0, 15.0, 20.3, 25.0, 27.0, 30.2, 33.3};

            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < lyrics.length; i++) {
                final int index = i;
                Thread t = new Thread(() -> singLyric(lyrics[index][0], delays[index], Double.parseDouble(lyrics[index][1])));
                threads.add(t);
                t.start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        public static void main(String[] args) {
            singSong();
        }
    }


