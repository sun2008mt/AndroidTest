package sun.geonoon.wh.androidtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.broadcast.BootCompleteReceiver;
import sun.geonoon.wh.androidtest.broadcast.BroadcastActivity;
import sun.geonoon.wh.androidtest.broadcast.LocalBroadcastActivity;
import sun.geonoon.wh.androidtest.database.CreateDBActivity;
import sun.geonoon.wh.androidtest.datapersistence.FilePersistenceActivity;
import sun.geonoon.wh.androidtest.datapersistence.SharedPreferenceActivity;
import sun.geonoon.wh.androidtest.forceoffline.LoginActivity;
import sun.geonoon.wh.androidtest.fragments.FragmentsActivity;
import sun.geonoon.wh.androidtest.messaging.MessagingActivity;
import sun.geonoon.wh.androidtest.news.NewsActivity;
import sun.geonoon.wh.androidtest.recyclerview.RecyclerViewActivity;
import sun.geonoon.wh.androidtest.widgets.TitleActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TitleActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MessagingActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FragmentsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //利用Intent对象发送自定义广播
                Intent intent = new Intent("sun.geonoon.wh.androidtest.MY_BROADCAST");
                //添加信息
                intent.putExtra("sender", "Marc");
                //默认是发送标准广播（异步）
                sendBroadcast(intent);
                //发送有序广播（同步，可拦截）
                sendOrderedBroadcast(intent, null);
            }
        });

        findViewById(R.id.btn8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LocalBroadcastActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "send force offline command", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("sun.geonoon.wh.androidtest.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });

        findViewById(R.id.btn11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FilePersistenceActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SharedPreferenceActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateDBActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.btn15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
