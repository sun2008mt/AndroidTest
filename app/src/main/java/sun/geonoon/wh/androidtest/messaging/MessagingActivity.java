package sun.geonoon.wh.androidtest.messaging;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sun.geonoon.wh.androidtest.R;

public class MessagingActivity extends Activity {

    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        initData();
        final RecyclerView recyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final MsgAdapter msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);

        Button btnSend = findViewById(R.id.send);
        final TextView txtInput = findViewById(R.id.txt_input);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = txtInput.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);

                    //当有新消息时，刷新RecyclerView中的显示
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    //滚动到新消息处
                    recyclerView.scrollToPosition(msgList.size() - 1);
                    //清空输入框中的内容
                    txtInput.setText("");
                }
            }
        });
    }


    private void initData() {
        Msg msg1 = new Msg("Hello guy. ", Msg.TYPE_RECEIVED);
        msgList.add(msg1);

        Msg msg2 = new Msg("Hello. Who is that? ", Msg.TYPE_SEND);
        msgList.add(msg2);

        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
