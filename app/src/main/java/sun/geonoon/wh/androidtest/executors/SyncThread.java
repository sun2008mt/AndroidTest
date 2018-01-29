package sun.geonoon.wh.androidtest.executors;

import android.util.Log;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SyncThread extends Thread {

    private static Object obj = new Object();

    SyncThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //执行耗时操作
        Log.e("SyncThread", "=========================");

        synchronized (obj) {
            for (int i = 0; i < 10; i++) {
                Log.e("SyncThread", this.getName() + " " + i);

                if (i % 4 == 0) {
                    try {
                        Thread.sleep(1000);                 //不会释放同步锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
