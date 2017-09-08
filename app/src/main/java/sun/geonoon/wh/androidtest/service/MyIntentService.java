package sun.geonoon.wh.androidtest.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2017/9/8.
 */

//在服务运行结束后会自动停止
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyIntentService: ", "onCreate executed");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前线程的id(封装了，在子线程中)
        Log.e("MyIntentService: ", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyIntentService: ", "onDestroy executed");
    }
}
