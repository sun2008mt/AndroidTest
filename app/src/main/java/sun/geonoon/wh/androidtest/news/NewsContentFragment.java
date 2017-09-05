package sun.geonoon.wh.androidtest.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

public class NewsContentFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_content, container, false);
        return rootView;
    }

    public void refresh(String newsTitle, String newsContent) {
        View layoutVisible = rootView.findViewById(R.id.layout_visible);
        layoutVisible.setVisibility(View.VISIBLE);

        TextView txtNewsTitle = rootView.findViewById(R.id.news_title);
        TextView txtNewsContent = rootView.findViewById(R.id.news_content);
        //刷新新闻标题和内容
        txtNewsTitle.setText(newsTitle);
        txtNewsContent.setText(newsContent);
    }
}
