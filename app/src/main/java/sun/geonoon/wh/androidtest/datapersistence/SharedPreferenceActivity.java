package sun.geonoon.wh.androidtest.datapersistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import sun.geonoon.wh.androidtest.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);

        Button btnSaveSharedPreference = (Button) findViewById(R.id.btn_save_shared_preference);
        Button btnReadSharedPreference = (Button) findViewById(R.id.btn_read_shared_preference);
        final TextView txtContent = (TextView) findViewById(R.id.txt_content);

        btnSaveSharedPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //只有一种MODE_PRIVATE模式，表示只有当前的应用程序才可以对这个SharedPreferences文件进行读写
                //采用Context类的getSharedPreferences()方法获取的对象文件存放在/data/data/<package name>/shared_prefs/<file name>目录下
                SharedPreferences.Editor editor = getSharedPreferences("save_shared_preference", MODE_PRIVATE).edit();
                editor.putString("name", "Marc");
                editor.putInt("age", 11);
                editor.putBoolean("married", false);
                editor.apply();
                Toast.makeText(SharedPreferenceActivity.this, "save to SharedPreferences successfully!", Toast.LENGTH_SHORT).show();
            }
        });

        //采用Activity类的getPreference()方法是使用此Activity的类名作为flie name
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        //采用PreferenceManager类中的getDefaultSharedPreferences()方法会使用当前应用程序的包名作为前缀来命名SharedPreferences文件
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        btnReadSharedPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("save_shared_preference", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);

                String content = "name: " + name + ", age: " + age + ", married: " + married;
                if (!TextUtils.isEmpty(content)) {
                    txtContent.setText(content);
                } else {
                    Toast.makeText(SharedPreferenceActivity.this, "SharedPreferences File not exists or is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
