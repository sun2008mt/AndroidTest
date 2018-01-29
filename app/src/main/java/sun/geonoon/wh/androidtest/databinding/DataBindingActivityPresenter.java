package sun.geonoon.wh.androidtest.databinding;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DataBindingActivityPresenter implements DataBindingActivityContract.Presenter {

    private DataBindingActivityContract.View view;

    private Context context;

    public DataBindingActivityPresenter(DataBindingActivityContract.View view) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onShowData(User user) {
        view.showData(user);
    }

    @Override
    public void showList() {
        Intent intent = new Intent(context, RecyclerViewDataBindingActivity.class);
        context.startActivity(intent);
    }
}
