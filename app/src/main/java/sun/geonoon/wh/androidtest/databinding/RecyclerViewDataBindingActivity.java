package sun.geonoon.wh.androidtest.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import sun.geonoon.wh.androidtest.R;

public class RecyclerViewDataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_data_binding);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {

    }
}
