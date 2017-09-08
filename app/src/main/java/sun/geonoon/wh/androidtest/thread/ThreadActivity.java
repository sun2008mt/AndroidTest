package sun.geonoon.wh.androidtest.thread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sun.geonoon.wh.androidtest.R;

public class ThreadActivity extends AppCompatActivity {

    public static final int UPDATE_TEXT = 1;

    private TextView text;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //在此可以进行UI操作（主线程中）
                    text.setText("Nice to meet you");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        text = (TextView) findViewById(R.id.txt);
        Button btn_change_txt = (Button) findViewById(R.id.btn_change_txt);
        btn_change_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);      //发送Message对象
                }).start();
            }
        });
    }
}
