package sun.geonoon.wh.androidtest.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Type> typeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        TypeAdapter typeAdapter = new TypeAdapter(typeList);
        recyclerView.setAdapter(typeAdapter);
    }

    private void initData() {
        String[] types = {"01", "05", "06", "07", "08", "09", "14", "15", "16", "98"};

//        for (int i = 0; i < types.length; i++) {
//        }

        Type type1 = new Type(R.drawable.area_01, "01");
        typeList.add(type1);

        Type type2 = new Type(R.drawable.area_05, "05");
        typeList.add(type2);

        Type type3 = new Type(R.drawable.area_06, "06");
        typeList.add(type3);

        Type type4 = new Type(R.drawable.area_07, "07");
        typeList.add(type4);

        Type type5 = new Type(R.drawable.area_08, "08");
        typeList.add(type5);

        Type type6 = new Type(R.drawable.area_09, "09");
        typeList.add(type6);

        Type type7 = new Type(R.drawable.area_14, "14");
        typeList.add(type7);

        Type type8 = new Type(R.drawable.area_15, "15");
        typeList.add(type8);

        Type type9 = new Type(R.drawable.area_16, "16");
        typeList.add(type9);

        Type type10 = new Type(R.drawable.area_98, "98");
        typeList.add(type10);
    }
}
