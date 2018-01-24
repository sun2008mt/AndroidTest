package sun.geonoon.wh.androidtest.mvp;

import android.Manifest;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.R;
import sun.geonoon.wh.androidtest.mvp.bean.UserBean;

public class UserActivity extends AppCompatActivity implements UserContract.View, View.OnClickListener {

    private EditText txtID, txtFirstName, txtLastName;
    private Button btnLoad, btnSave;

    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();

        userPresenter = new UserPresenter(this);
    }

    private void initView() {
        txtID = findViewById(R.id.id_edt);
        txtFirstName = findViewById(R.id.first_name_edt);
        txtLastName = findViewById(R.id.last_name_edt);
        btnLoad = findViewById(R.id.loadButton);
        btnSave = findViewById(R.id.saveButton);
        btnLoad.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public int getID() {
        String id = txtID.getText().toString();

        int res = -1;
        try {
            res = Integer.parseInt(id);
            return res;
        } catch (Exception e) {
            Toast.makeText(UserActivity.this, "读取信息需要输入正确的ID...", Toast.LENGTH_SHORT).show();
        }

        return res;
    }

    @Override
    public String getFirstName() {
        return txtFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return txtLastName.getText().toString();
    }

    @Override
    public void setUser(UserBean user) {
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadButton:
                userPresenter.loadUser(getID());
                break;

            case R.id.saveButton:
                userPresenter.setUser(getID(), getFirstName(), getLastName());
                break;

            default:
                break;
        }
    }
}
