package sun.geonoon.wh.androidtest.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import sun.geonoon.wh.androidtest.R;

public class ServiceActivity extends AppCompatActivity {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("ServiceActivity: ", "onServiceConnected");
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("ServiceActivity: ", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.start_service).setOnClickListener(view -> {
            Intent startIntent = new Intent(ServiceActivity.this, MyService.class);
            //启动服务
            startService(startIntent);
        });

        findViewById(R.id.stop_service).setOnClickListener(view -> {
            Intent stopIntent = new Intent(ServiceActivity.this, MyService.class);
            stopService(stopIntent);
        });

        findViewById(R.id.bind_service).setOnClickListener(view -> {
            Intent bindIntent = new Intent(ServiceActivity.this, MyService.class);
            //绑定服务
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        });

        findViewById(R.id.unbind_service).setOnClickListener(view -> {
            Intent unbindIntent = new Intent(ServiceActivity.this, MyService.class);
            unbindService(connection);
        });
    }
}
