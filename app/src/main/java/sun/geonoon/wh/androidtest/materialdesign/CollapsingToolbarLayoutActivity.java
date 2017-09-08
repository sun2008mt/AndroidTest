package sun.geonoon.wh.androidtest.materialdesign;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import sun.geonoon.wh.androidtest.R;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity {

    public static final String TYPE_NAME = "fruit_name";

    public static final String TYPE_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);

        Intent intent = getIntent();
        String typeName = intent.getStringExtra(TYPE_NAME);
        int typeImageId = intent.getIntExtra(TYPE_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView typeImageView = (ImageView) findViewById(R.id.img_view_type);
        TextView typeContentText = (TextView) findViewById(R.id.txt_type_content);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(typeName);
        typeImageView.setImageResource(typeImageId);
        String typeContent = generateTypeContent(typeName);
        typeContentText.setText(typeContent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String generateTypeContent(String typeName) {
        StringBuilder typeContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            typeContent.append(typeName + " ");
        }

        return typeContent.toString();
    }
}
