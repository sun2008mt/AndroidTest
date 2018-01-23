package sun.geonoon.wh.androidtest.mvp;

import sun.geonoon.wh.androidtest.mvp.bean.UserBean;

/**
 * Created by Administrator on 2018/1/23.
 */

public interface UserContract {
    interface View {

        int getID();

        String getFirstName();

        String getLastName();

        void setUser(UserBean user);
    }

    interface Presenter {
        void setUser(int id, String firstName, String lastName);

        void loadUser(int id);
    }
}
