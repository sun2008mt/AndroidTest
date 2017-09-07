package sun.geonoon.wh.androidtest.materialdesign;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sun.geonoon.wh.androidtest.MainActivity;
import sun.geonoon.wh.androidtest.R;
import sun.geonoon.wh.androidtest.recyclerview.Type;

public class MaterialDesignActivity extends AppCompatActivity {

    private static Type[] types = {
            new Type(R.drawable.area_01, "Area 01"),
            new Type(R.drawable.area_05, "Area 01"),
            new Type(R.drawable.area_06, "Area 01"),
            new Type(R.drawable.area_07, "Area 01"),
            new Type(R.drawable.area_08, "Area 01"),
            new Type(R.drawable.area_09, "Area 01"),
            new Type(R.drawable.area_14, "Area 01"),
            new Type(R.drawable.area_15, "Area 01"),
            new Type(R.drawable.area_16, "Area 01"),
            new Type(R.drawable.area_98, "Area 01")
    };

    private DrawerLayout mDrawerLayout;

    private List<Type> typeList = new ArrayList<>();

    private TypeCardViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matirial_design);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        //此ActionBar的具体实现是由ToolBar来完成的
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //显示导航按钮(HomeAsUp按钮，id为android.R.id.home)
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮图标
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        //将Call菜单项设置为默认选中
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MaterialDesignActivity.this, "You clicked on " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_floating);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //第一个参数表示当前界面任意控件，Snackbar会通过这个View自动查找最外层的布局
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MaterialDesignActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        initTypes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TypeCardViewAdapter(typeList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    private void initTypes() {
        typeList.clear();

        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(types.length);
            typeList.add(types[index]);
        }
    }
}
