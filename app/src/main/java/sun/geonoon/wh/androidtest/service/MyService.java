package sun.geonoon.wh.androidtest.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import sun.geonoon.wh.androidtest.MainActivity;
import sun.geonoon.wh.androidtest.R;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    public MyService() {
    }

    //在服务第一次创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService: ", "onCreate executed");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    //在每次启动服务的时候都会调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService: ", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService: ", "onDestroy executed");
    }

    //与活动(Activity)关联
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {

        private int count = 0;

        public void startDownload() {
            Log.e("MyService: ", "startDownload executed");
        }

        public int getProgress() {
            Log.e("MyService: ", "getProgress executed");
            try {
                count++;
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("count: ", count + "");
            return count;
        }
    }
}
