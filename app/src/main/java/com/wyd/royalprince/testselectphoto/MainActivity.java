package com.wyd.royalprince.testselectphoto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import com.wyd.royalprince.testselectphoto.selectphotoview.PickOrTakeImageActivity;

/**
 * Created by wyd on 2018/3/15.
 */
public class MainActivity extends AppCompatActivity {

    public final int REQUEST_CODE_PICK_IMAGE = 1;
    public final int REQUEST_CODE_PICK_IMAGE_2 = 2;
    public final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 4;
    public final int MY_PERMISSIONS_REQUEST_CALL_PHONE2 = 5;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        findViewById(R.id.open_photo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_CALL_PHONE2);

                } else {
                    choosePhoto();
                }
            }
        });

        findViewById(R.id.open_my_photo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PickOrTakeImageActivity.class);
                startActivityForResult(intent,REQUEST_CODE_PICK_IMAGE_2);
            }
        });
    }

    public void choosePhoto() {
        /**
         * 打开选择图片的界面
         */
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");//相片类型
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

    }

    public void onActivityResult(int req, int res, Intent data) {
        switch (req) {
            /**
             * 拍照的请求标志
             */
//            case CROP_PHOTO:
//                if (res == RESULT_OK) {
//                    try {
//                        /**
//                         * 该uri就是照片文件夹对应的uri
//                         */
//                        Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        imageView.setImageBitmap(bit);
//                    } catch (Exception e) {
//                        Toast.makeText(this, "程序崩溃", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Log.i("tag", "失败");
//                }
//
//                break;
            /**
             * 从相册中选取图片的请求标志
             */

            case REQUEST_CODE_PICK_IMAGE:
                if (res == RESULT_OK) {
                    try {
                        /**
                         * 该uri是上一个Activity返回的
                         */
                        Uri uri = data.getData();
                        Log.e("-------->uri", "---->"+uri.getPath());
                        Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageView.setImageBitmap(bit);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("-------->tag", e.getMessage());
                        Toast.makeText(this, "程序崩溃", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("------->", "失败");
                }

                break;
            case REQUEST_CODE_PICK_IMAGE_2:

                break;

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                takePhoto();
                Toast.makeText(MainActivity.this, "拍照权限通过", Toast.LENGTH_SHORT).show();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choosePhoto();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

