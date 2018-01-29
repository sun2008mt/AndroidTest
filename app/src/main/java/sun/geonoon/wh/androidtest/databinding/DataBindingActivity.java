package sun.geonoon.wh.androidtest.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import sun.geonoon.wh.androidtest.R;

public class DataBindingActivity extends AppCompatActivity implements DataBindingActivityContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //生成的数据绑定类继承ViewDataBinding基类，并且命名根据layout文件名，在后面加上Binding后缀
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        DataBindingActivityPresenter presenter = new DataBindingActivityPresenter(this, this);

        User user = new User("marc", "22", "http://lorempixel.com/40/40/");
        binding.setUser(user);
        binding.setPresenter(presenter);


    }

    @Override
    public void showData(User user) {
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
    }
}
