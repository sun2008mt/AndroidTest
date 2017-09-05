package sun.geonoon.wh.androidtest.widgets;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

//如果继承AppCompatActivity，则需要使用AppCompat的主题；如果继承Activity，则使用android:Theme的主题
public class TitleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

//        隐藏标题栏
        ActionBar actionBar = getActionBar();        //由于继承的是AppCompatActivity，则使用getActionBar会返回null， 需要使用getSupportActionBar方法
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }
}
