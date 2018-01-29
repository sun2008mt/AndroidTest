package sun.geonoon.wh.androidtest.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import sun.geonoon.wh.androidtest.R;

public class RecyclerViewDataBindingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_data_binding);

        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<User> items = Arrays.asList(new User("Marc", "18", "http://lorempixel.com/40/40/"),
                                            new User("Test", "1", "http://lorempixel.com/40/40/"),
                                            new User("Lily", "15", "http://lorempixel.com/40/40/"),
                                            new User("John", "51", "http://lorempixel.com/40/40/"),
                                            new User("Sophia", "14", "http://lorempixel.com/40/40/")
                );

        mAdapter = new MyAdapter(items);
        recyclerView.setAdapter(mAdapter);
    }

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url) {
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.area_01)
                .into(view);
    }
}
