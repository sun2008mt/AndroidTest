package sun.geonoon.wh.androidtest.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    //广播是一种可以跨进程通信的方式,其他程序也可以接收到该action值的广播

    @Override
    public void onReceive(Context context, Intent intent) {
        String sender = intent.getStringExtra("sender");
        Toast.makeText(context, "receive in MyBroadcastReceiver, the sender is: " + sender, Toast.LENGTH_SHORT).show();

        //接收有序广播后可以进行拦截（终止下一个接收者的接收）
        abortBroadcast();
    }
}
