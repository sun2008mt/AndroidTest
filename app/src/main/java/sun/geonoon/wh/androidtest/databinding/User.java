package sun.geonoon.wh.androidtest.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.renderscript.BaseObj;

import sun.geonoon.wh.androidtest.BR;

/**
 * Created by Administrator on 2018/1/23.
 */

public class User extends BaseObservable {
    private String name;
    private String age;
    private String url;

    public User(String name, String age, String url) {
        this.name = name;
        this.age = age;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public void setAge(String age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }

    @Bindable
    public String getName() {
        return this.name;
    }

    @Bindable
    public String getAge() {
        return this.age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return name + ": " + age;
    }
}
