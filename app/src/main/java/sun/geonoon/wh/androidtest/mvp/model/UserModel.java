package sun.geonoon.wh.androidtest.mvp.model;

import android.util.SparseArray;

import sun.geonoon.wh.androidtest.mvp.bean.UserBean;

/**
 * Created by Administrator on 2018/1/23.
 */

public class UserModel implements IUserModel {

    private SparseArray<UserBean> mUserArray = new SparseArray<>();

    @Override
    public UserBean load(int id) {
        UserBean userBean = mUserArray.get(id);
        return userBean;
    }

    @Override
    public void setUser(int id, String firstName, String lastName) {
        UserBean userBean = mUserArray.get(id);
        if (userBean != null) {
            userBean.setFirstName(firstName);
            userBean.setLastName(lastName);
        } else {
            userBean = new UserBean(firstName, lastName);
            mUserArray.put(id, userBean);
        }
    }

}
