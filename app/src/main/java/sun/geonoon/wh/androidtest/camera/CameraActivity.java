package sun.geonoon.wh.androidtest.camera;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    public static final int CHOOSE_PHOTO = 2;

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

        findViewById(R.id.btn_choose_from_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //相册中的照片是存放在SD卡上的,需要动态申请WRITE_EXTERNAL_STORAGE权限
                if (ActivityCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKEN_PHOTO:
                if (RESULT_OK == resultCode) {

                    //将拍摄的照片显示出来
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case CHOOSE_PHOTO:
                if (RESULT_OK == resultCode) {
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统处理图片的方法
                        handleImageAfterKitKat(data);
                    } else {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;

            default:
                Toast.makeText(CameraActivity.this, "default activity result", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(CameraActivity.this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //打开相册
    private void openAlbum() {
        //打开相册
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @TargetApi(19)
    private void handleImageAfterKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();

        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //authority是media格式
                String id = docId.split(":")[1];      //解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                //authority是downloads格式
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的Uri， 则使用普通的处理方式
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }

        displayImage(imagePath);
    }

    //Android4.4之前处理图片方法
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();

        String imagePath = getImagePath(uri, null);
        Log.e("imagePath: ", imagePath);
        displayImage(imagePath);
    }

    //获取图片的真实路径
    private String getImagePath(Uri uri, String selection) {
        String path = null;

        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

        return path;
    }

    //根据图片路径显示图片
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }
}
