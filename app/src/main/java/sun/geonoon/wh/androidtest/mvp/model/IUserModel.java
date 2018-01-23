package sun.geonoon.wh.androidtest.mvp.model;

import sun.geonoon.wh.androidtest.mvp.bean.UserBean;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface IUserModel {

    //根据id获取用户信息
    UserBean load(int id);

    //改变用户信息
    void setUser(int id, String firstName, String lastName);
}
