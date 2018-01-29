package sun.geonoon.wh.androidtest.executors;

import android.util.Log;

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
        Log.e("SyncThread2", "=========================");

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                Log.e("SyncThread2", this.getName() + " " + i);

                if (i % 4 == 0) {
                    Thread.yield();
                }
            }
        }
    }
}
