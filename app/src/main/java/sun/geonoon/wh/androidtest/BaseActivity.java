package sun.geonoon.wh.androidtest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import sun.geonoon.wh.androidtest.forceoffline.ActivityCollector;
import sun.geonoon.wh.androidtest.forceoffline.LoginActivity;

/**
 * Created by marc on 17-9-5.
 */

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    //保证只有处于栈顶的活动才能收到强制下线的广播
    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("sun.geonoon.wh.androidtest.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            new AlertDialog.Builder(context)
                    .setTitle("Warning")
                    .setMessage("You are forced to be offline. Please try to login again.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //销毁所有活动
                            ActivityCollector.finishAll();
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                    })
                    .show();
        }
    }
}
