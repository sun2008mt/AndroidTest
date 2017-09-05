package sun.geonoon.wh.androidtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sun.geonoon.wh.androidtest.fragments.FragmentsActivity;
import sun.geonoon.wh.androidtest.messaging.MessagingActivity;
import sun.geonoon.wh.androidtest.news.NewsActivity;
import sun.geonoon.wh.androidtest.recyclerview.RecyclerViewActivity;
import sun.geonoon.wh.androidtest.widgets.TitleActivity;

public class MainActivity extends Activity {

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
    }
}
