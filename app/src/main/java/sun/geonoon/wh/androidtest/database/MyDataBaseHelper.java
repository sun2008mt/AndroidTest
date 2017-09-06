package sun.geonoon.wh.androidtest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by marc on 17-9-6.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    //数据库文件会存放在/data/data/<package name>/databases/<db name>目录下
    public static final String CREATE_BOOK =
            "create table Book ("
                    + "id integer primary key autoincrement, "
                    + "author text, "
                    + "price real, "
                    + "pages integer, "
                    + "name text)";

    public static final String CREATE_CATEGORY =
            "create table Category ("
                    + "id integer primary key autoincrement, "
                    + "category_name text, "
                    + "category_code integer)";

    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //只有在创建的时候才会被调用，若数据库已经存在，不会再次被调用
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "DB created successfully!", Toast.LENGTH_SHORT).show();
    }

    //当传入的版本号比当前版本号大的时候会调用此方法
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop database if exists BookStore");
        sqLiteDatabase.execSQL("drop table if exists BOOK");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);
    }
}
