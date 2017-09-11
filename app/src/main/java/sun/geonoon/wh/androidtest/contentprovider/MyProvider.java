package sun.geonoon.wh.androidtest.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/9/11.
 */

public class MyProvider extends ContentProvider {

    public static final int ITABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final int TABLE2_DIR = 2;

    public static final int TABLE2_ITEM = 3;

    public static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("sun.geonoon.wh.androidtest.provider", "table1", TABLE2_DIR);
        uriMatcher.addURI("sun.geonoon.wh.androidtest.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("sun.geonoon.wh.androidtest.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("sun.geonoon.wh.androidtest.provider", "table2/#", TABLE2_ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        switch (uriMatcher.match(uri)) {
            case ITABLE1_DIR:
                //查詢table1表中的所有數據
                break;

            case TABLE1_ITEM:
                //查詢table1表中的单条数据
                break;

            case TABLE2_DIR:
                //查询table2表中的所有数据
                break;

            case TABLE2_ITEM:
                //查询table2表中的单条数据
                break;

            default:
                break;
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ITABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.sun.geonoon.wh.androidtest.provider.table1";

            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.sun.geonoon.wh.androidtest.provider.table1";

            case TABLE2_DIR:
                return "vnd.android.cursor.dir/vnd.sun.geonoon.wh.androidtest.provider.table2";

            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.sun.geonoon.wh.androidtest.provider.table2";

            default:
                break;
        }

        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
