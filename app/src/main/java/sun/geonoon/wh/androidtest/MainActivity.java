package sun.geonoon.wh.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.audio.PlayAudioActivity;
import sun.geonoon.wh.androidtest.broadcast.BroadcastActivity;
import sun.geonoon.wh.androidtest.broadcast.LocalBroadcastActivity;
import sun.geonoon.wh.androidtest.camera.CameraActivity;
import sun.geonoon.wh.androidtest.contentprovider.ReadContactsActivity;
import sun.geonoon.wh.androidtest.database.CreateDBActivity;
import sun.geonoon.wh.androidtest.datapersistence.FilePersistenceActivity;
import sun.geonoon.wh.androidtest.datapersistence.SharedPreferenceActivity;
import sun.geonoon.wh.androidtest.forceoffline.LoginActivity;
import sun.geonoon.wh.androidtest.fragments.FragmentsActivity;
import sun.geonoon.wh.androidtest.lbs.LBSActivity;
import sun.geonoon.wh.androidtest.materialdesign.MaterialDesignActivity;
import sun.geonoon.wh.androidtest.messaging.MessagingActivity;
import sun.geonoon.wh.androidtest.network_xml.HttpActivity;
import sun.geonoon.wh.androidtest.news.NewsActivity;
import sun.geonoon.wh.androidtest.notification.NotificationActivity;
import sun.geonoon.wh.androidtest.recyclerview.RecyclerViewActivity;
import sun.geonoon.wh.androidtest.runtimepermissions.CallPhoneActivity;
import sun.geonoon.wh.androidtest.service.ServiceActivity;
import sun.geonoon.wh.androidtest.thread.DownloadActivity;
import sun.geonoon.wh.androidtest.thread.ThreadActivity;
import sun.geonoon.wh.androidtest.video.PlayVideoActivity;
import sun.geonoon.wh.androidtest.webview.WebViewActivity;
import sun.geonoon.wh.androidtest.widgets.TitleActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TitleActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn2).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn3).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MessagingActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn4).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FragmentsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn5).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn6).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn7).setOnClickListener(view -> {
            //利用Intent对象发送自定义广播
            Intent intent = new Intent("sun.geonoon.wh.androidtest.MY_BROADCAST");
            //添加信息
            intent.putExtra("sender", "Marc");
            //默认是发送标准广播（异步）
            sendBroadcast(intent);
            //发送有序广播（同步，可拦截）
            sendOrderedBroadcast(intent, null);
        });

        findViewById(R.id.btn8).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LocalBroadcastActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn9).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.btn10).setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "send force offline command", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("sun.geonoon.wh.androidtest.FORCE_OFFLINE");
            sendBroadcast(intent);
        });

        findViewById(R.id.btn11).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FilePersistenceActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn12).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SharedPreferenceActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn13).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreateDBActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn14).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CallPhoneActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn15).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ReadContactsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn16).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn17).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn18).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn19).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HttpActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn20).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LBSActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn21).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MaterialDesignActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn22).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn23).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn24).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn25).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlayAudioActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn26).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
            startActivity(intent);
        });
    }
}
