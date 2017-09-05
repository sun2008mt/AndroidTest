package sun.geonoon.wh.androidtest.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sun.geonoon.wh.androidtest.R;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra("news_title");
        String newsContent = getIntent().getStringExtra("news_content");

        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_news_content);
        newsContentFragment.refresh(newsTitle, newsContent);
    }

    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }
}
