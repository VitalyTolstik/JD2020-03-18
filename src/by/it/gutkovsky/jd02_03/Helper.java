package by.it.gutkovsky.jd02_03;

import java.util.Random;

class Helper {

    private static final Random generator = new Random();

    private Helper() {
    }

    public static int getRandom(int start, int end) {
        return start + generator.nextInt(end - start + 1);
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout / Manager.K_SPEED);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted " + Thread.currentThread(), e);
        }
    }
}
