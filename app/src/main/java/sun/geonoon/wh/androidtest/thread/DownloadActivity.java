package sun.geonoon.wh.androidtest.thread;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.R;

public class DownloadActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("下载进度...");
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setCancelable(true);
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(DownloadActivity.this, "执行终止操作...", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进行下载任务
                new DownloadTask().execute();
            }
        });
    }

    class DownloadTask extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected void onPreExecute() {
            //显示进度对话框
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            //关闭进度对话框
            progressDialog.dismiss();

            if (result) {
                Toast.makeText(DownloadActivity.this, "Download succeeded!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DownloadActivity.this, "Download failed!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新下载进度
            progressDialog.setMessage("Downloaded " + values[0] + "%");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                while (true) {
                    int downloadPercent = doDownload();
                    publishProgress(downloadPercent);
                    if (downloadPercent >= 100) {
                        break;
                    }
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    private int count = 0;

    private int doDownload() {
        try {
            count++;
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return count;
    }

}
