package sun.geonoon.wh.androidtest.datapersistence;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import sun.geonoon.wh.androidtest.R;

public class FilePersistenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_persistence);

        final EditText txtEdit = (EditText) findViewById(R.id.txt_edit);
        Button btnSaveFile = (Button) findViewById(R.id.btn_save_file);
        Button btnReadFile = (Button) findViewById(R.id.btn_read_file);
        final TextView txtContent = (TextView) findViewById(R.id.txt_content);

        btnSaveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!"".equals(txtEdit.getText().toString())) {
                    save(txtEdit.getText().toString());
                    //默认会存储到内部存储空间的/data/data/<package name>/files/<file name>目录下
                    Toast.makeText(FilePersistenceActivity.this, "save file successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FilePersistenceActivity.this, "please input some words", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = load();
                if (!"".equals(data)) {
                    txtContent.setText(data);
                    Toast.makeText(FilePersistenceActivity.this, "read file successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FilePersistenceActivity.this, "File not exists or is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            //私有模式是默认模式，表示若文件不存在则创建，存在则覆盖原文件；另一种模式是Context.MODE_APPEND，表示是在原文件后添加
            out = openFileOutput("file_save", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();

        try {
            in = openFileInput("file_save");
            reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }
}
