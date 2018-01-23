package sun.geonoon.wh.androidtest.databinding;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface DataBindingActivityContract {
    public interface Presenter {
        void onShowData(User user);
    }

    public interface View {
        void showData(User user);
    }
}
