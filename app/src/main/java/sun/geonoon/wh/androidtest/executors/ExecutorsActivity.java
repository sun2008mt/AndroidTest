package sun.geonoon.wh.androidtest.executors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import sun.geonoon.wh.androidtest.R;

public class ExecutorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executors);

        findViewById(R.id.thread_test).setOnClickListener(v -> {
            //测试线程
            testThread();
        });
    }

    private void testThread() {
        Thread currentThread = Thread.currentThread();
        Log.e("MainThread", "=========================");
        Log.e("MainThread", currentThread.getName());

        //sleep()不会释放所持有的对象同步锁
        //当一个线程处于睡眠状态时，若被其他线程调用interrupt()方法中断，则sleep()方法会抛出InterruptedException异常
        SyncThread thread1 = new SyncThread("thread 1");
        SyncThread thread2 = new SyncThread("thread 2");
        SyncThread thread3 = new SyncThread("thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

        //yield不会释放所持有的对象同步锁
        SyncThread2 thread21 = new SyncThread2("thread 21");
        SyncThread2 thread22 = new SyncThread2("thread 22");
        SyncThread2 thread23 = new SyncThread2("thread 23");

        thread21.start();
        thread22.start();
        thread23.start();

        SyncRunnable syncRunnable = new SyncRunnable();
        Thread thread4 = new Thread(syncRunnable, "thread 4");
        Thread thread5 = new Thread(syncRunnable, "thread 5");
        Thread thread6 = new Thread(syncRunnable, "thread 6");

        thread4.start();
        thread5.start();
        thread6.start();
    }
}
