package sun.geonoon.wh.androidtest.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

import sun.geonoon.wh.androidtest.MainActivity;
import sun.geonoon.wh.androidtest.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btn_send_notice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                //相当于延迟启动的Intent
                PendingIntent pi = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(NotificationActivity.this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setContentIntent(pi)               //点击notification会执行相应的动作
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))             //设置通知发出的时候播放的音频文件
                        //先静止，然后震动一秒，再静止一秒，最后震动一秒
                        .setVibrate(new long[] {0, 1000, 1000, 1000})                     //设置通知发出时震动
                        //LED先绿色亮1秒，然后暗1秒（一闪一闪）
                        .setLights(Color.GREEN, 1000, 1000)                           //设置通知发出时LED闪烁
//                        .setDefaults(NotificationCompat.DEFAULT_ALL)               //采用默认设置
                        .setAutoCancel(true)          //点击后取消notification
                        .build();
                manager.notify(1, notification);
            }
        });
    }
}
