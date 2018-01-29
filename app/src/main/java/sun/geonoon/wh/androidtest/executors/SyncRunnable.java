package sun.geonoon.wh.androidtest.executors;

import android.util.Log;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SyncRunnable implements Runnable {

    @Override
    public void run() {
        //执行耗时操作
        Log.e("SyncRunnable", "=========================");
    }
}
