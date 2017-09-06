package sun.geonoon.wh.androidtest.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.geonoon.wh.androidtest.MainActivity;
import sun.geonoon.wh.androidtest.R;

public class CameraActivity extends AppCompatActivity {

    public static final int TAKEN_PHOTO = 1;

    private Uri imageUri;

    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        picture = (ImageView) findViewById(R.id.picture_taken);

        findViewById(R.id.btn_take_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建File对象，用于存储拍照后的图片
                //getExternalCacheDir对应的是SD卡的应用关联缓存目录（SD卡中专门用于存放当前应用缓存数据的位置）,具体路径为/sdcard/Android/data/<package name>/cache
                //从Android6.0开始，读写SD卡被列为了危险权限，需要提供运行时权限，不过应用关联目录不需要受此限制
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                if (outputImage.exists()) {
                    outputImage.delete();
                }

                try {
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Android 7.0以上，直接使用本地真实路径的Uri被认为是不安全的，会抛出一个FileUriExposedException异常
                if (Build.VERSION.SDK_INT >= 24) {
                    //FileProvider是ContentProvider的一种，需要在Manifest文件中进行注册
                    imageUri = FileProvider.getUriForFile(CameraActivity.this, "sun.geonoon.wh.androidtest.fileprovider", outputImage);        //此方法对Uri对象进行了封装
                } else {
                    imageUri = Uri.fromFile(outputImage);          //Uri标识着文件的本地真实路径
                }

                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");        //隐式Intent，系统会找到相机程序响应此Intent
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);         //指定图片输出地址
                startActivityForResult(intent, TAKEN_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKEN_PHOTO:
                if (resultCode == RESULT_OK) {

                    //将拍摄的照片显示出来
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            default:
                Toast.makeText(CameraActivity.this, "default activity result", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
