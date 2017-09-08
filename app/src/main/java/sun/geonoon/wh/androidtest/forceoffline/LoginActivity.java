package sun.geonoon.wh.androidtest.forceoffline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.MainActivity;
import sun.geonoon.wh.androidtest.R;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private CheckBox rememberPassword;

    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPassword = (CheckBox) findViewById(R.id.remember_password);
        loginBtn = (Button) findViewById(R.id.login);

        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //若获取的值不存在，则默认为false
        boolean remember_password = defaultSharedPreferences.getBoolean("remember_password", false);
        if (remember_password) {
            String account = defaultSharedPreferences.getString("account", "");
            String password = defaultSharedPreferences.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPassword.setChecked(true);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //如果账号是admin，密码是123456，认为登录成功
                if ("admin".equals(account) && "123456".equals(password)) {
                    SharedPreferences.Editor edit = defaultSharedPreferences.edit();

                    if (rememberPassword.isChecked()) {
                        //如果记住密码则写入SharedPreferences文件中
                        edit.putBoolean("remember_password", true);
                        edit.putString("account", account);
                        edit.putString("password", password);
                    } else {
                        //如果取消记住密码则清空SharedPreferences文件信息
                        edit.clear();
                    }
                    //提交更改
                    edit.apply();

                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
