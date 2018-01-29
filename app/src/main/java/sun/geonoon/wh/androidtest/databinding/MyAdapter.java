package sun.geonoon.wh.androidtest.databinding;

import java.util.List;

import sun.geonoon.wh.androidtest.R;

/**
 * Created by Administrator on 2018/1/29.
 */

public class MyAdapter extends MyBaseAdapter {

    List<User> data;

    public MyAdapter(List<User> users) {
        data = users;
    }

    @Override
    public Object getDataAtPosition(int position) {
        return data.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.item_user_recyclerview;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
