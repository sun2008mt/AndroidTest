package sun.geonoon.wh.androidtest.mvp;

import sun.geonoon.wh.androidtest.mvp.bean.UserBean;
import sun.geonoon.wh.androidtest.mvp.model.IUserModel;
import sun.geonoon.wh.androidtest.mvp.model.UserModel;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserPresenter implements UserContract.Presenter {

    private IUserModel mUserModel;
    private UserContract.View mView;

    public UserPresenter(UserContract.View view) {
        mView = view;
        mUserModel = new UserModel();
    }

    @Override
    public void setUser(int id, String firstName, String lastName) {
        mUserModel.setUser(id, firstName, lastName);
    }

    @Override
    public void loadUser(int id) {
        UserBean userBean = mUserModel.load(id);
        if (userBean == null) {
            userBean = new UserBean("not found", "not found");
        }

        mView.setUser(userBean);
    }
}
