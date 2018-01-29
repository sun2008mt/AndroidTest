package sun.geonoon.wh.androidtest.executors;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SyncThread2 extends Thread {

    private static Object obj = new Object();

    public SyncThread2(String name) {
        super(name);
    }

    @Override
    public void run() {

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s: %d\n", this.getName(), i);

                if (i % 4 == 0) {
                    Thread.yield();
                }
            }
        }
    }
}
