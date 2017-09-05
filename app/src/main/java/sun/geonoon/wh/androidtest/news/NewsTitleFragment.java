package sun.geonoon.wh.androidtest.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by marc on 17-9-5.
 */

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_title, container, false);

        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);

        return view;
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            News news = new News("This is news title " + i, "This is news content " + i);
            newsList.add(news);
        }

        return newsList;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.layout_news_content) != null) {
            //可以找到news_content布局时，为双页模式
            isTwoPane = true;
        } else {
            //找不到nwes_content布局时，为单页模式
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        //如果是双页模式，则刷新NewsContentFragment中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.fragment_news_content);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        //如果是单页模式，则直接启动NewsContentActivity
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.txtNewsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtNewsTitle;

            public ViewHolder(View itemView) {
                super(itemView);
                txtNewsTitle = itemView.findViewById(R.id.item_news_title);
            }
        }
    }
}
