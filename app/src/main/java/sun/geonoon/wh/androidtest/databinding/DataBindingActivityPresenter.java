package sun.geonoon.wh.androidtest.databinding;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DataBindingActivityPresenter implements DataBindingActivityContract.Presenter {

    private DataBindingActivityContract.View view;

    public DataBindingActivityPresenter(DataBindingActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void onShowData(User user) {
        view.showData(user);
    }
}
