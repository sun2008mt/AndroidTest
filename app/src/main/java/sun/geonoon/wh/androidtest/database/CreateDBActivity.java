package sun.geonoon.wh.androidtest.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import sun.geonoon.wh.androidtest.R;

public class CreateDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_db);

        final MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(this, "BookStore.db", null, 2);

        final TextView txtContent = (TextView) findViewById(R.id.txt_content);

        findViewById(R.id.btn_create_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //若不存在该数据库，则会创建数据库并调用MyDatabaseHelper中的onCreate()方法创建表
                myDataBaseHelper.getWritableDatabase();
            }
        });

        findViewById(R.id.btn_add_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
//                //添加的数据键值对
//                values.put("name", "The Da Vinci Code");
//                values.put("author", "Dan Brown");
//                values.put("pages", 454);
//                values.put("price", 16.96);
//                //插入第一条数据
//                db.insert("Book", null, values);
//                //清空ContentValues对象
//                values.clear();
//
//                values.put("name", "The Lost Symbol");
//                values.put("author", "Dan Brown");
//                values.put("pages", 510);
//                values.put("price", 19.95);
//                //插入第二条数据
//                db.insert("Book", null, values);

                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)", new String[] {"The Da Vinci Code", "Dan Brown", "454", "16.96"});
                db.execSQL("insert into Book(name, author, pages, price) values(?, ?, ?, ?)", new String[] {"The Lost Symbol", "Dan Brown", "510", "19.95"});
            }
        });

        findViewById(R.id.btn_update_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("price", 10.99);
//                db.update("Book", values, "name = ?", new String[] {"The Da Vinci Code"});
                db.execSQL("update Book set price = ? where name = ?", new String[] {"10.99", "The Da Vinci Code"});
            }
        });

        findViewById(R.id.btn_delete_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
//                db.delete("Book", "pages < ?", new String[]{ "600" });
                db.execSQL("delete from Book where pages < ?", new String[]{"600"});
            }
        });

        findViewById(R.id.btn_query_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = myDataBaseHelper.getWritableDatabase();
//                Cursor cursor = db.query("Book", null, "name = ?", new String[] {"The Da Vinci Code"}, null, null, null);
//                Cursor cursor = db.rawQuery("select * from Book where name = ?", new String[]{"The Da Vinci Code"});
                Cursor cursor = db.rawQuery("select * from Book", null);

                StringBuilder content = new StringBuilder();
                if (cursor.moveToFirst()) {
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        content.append("[name: ").append(name)
                                .append(", author: ").append(author)
                                .append(", pages: ").append(pages)
                                .append(", price: ").append(price).append("]");

                    } while (cursor.moveToNext());
                }
                cursor.close();

                txtContent.setText(content.toString());
            }
        });
    }
}
