package com.prd.testpicupload;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.prd.testpicupload.widget.SelectPicPopup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    private SelectPicPopup spp;

    private Button btn_click;
    private ImageView iv_tu;

    private File file;
    private String filename;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_click = (Button) findViewById(R.id.btn_click);

        iv_tu = (ImageView) findViewById(R.id.iv_tu);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spp = new SelectPicPopup(MainActivity.this,itemOnClick);
                spp.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

    }

    View.OnClickListener itemOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            spp.dismiss();

            switch (v.getId()) {

                case R.id.btn_pick_photo:
                    Log.e("tag","从相册选择");

                    Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                    intent1.setDataAndType(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent1, 200);
                    break;

                case R.id.btn_take_photo:

                    Log.e("tag","拍照");

                    String state = Environment.getExternalStorageState();
                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        String saveDir = Environment.getExternalStorageDirectory()
                                + "/prd/image";
                        File dir = new File(saveDir);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        filename = System.currentTimeMillis() + ".jpg";
                        file = new File(saveDir, filename);
                        file.delete();
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                    }
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(intent, 100);

                    break;

            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String path = null;

        switch (requestCode) {
            case 100:

                path = Environment.getExternalStorageDirectory()
                        + "/prd/image/" + filename;

                break;

            case 200:

                if (data != null && data.getData() != null) {
                    // 根据返回的URI获取对应的SQLite信息
                    Uri uri = data.getData();

                    final String[] proj = { MediaStore.Images.Media.DATA };
                    Cursor cursor = null;
                    try {
                        cursor = this.getContentResolver().query(uri, proj,
                                null, null, null);
                        int column_index = cursor
                                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        path = cursor.getString(column_index);
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                            cursor = null;
                        }
                    }
                }

                break;

            case 300:

                if (resultCode != RESULT_OK) {
                    return;
                }

                if (data == null) {
                    return;
                }

                Bundle bundle = data.getExtras();

                Bitmap bitmap = bundle.getParcelable("data");

                if (bitmap != null) {
                    filename = System.currentTimeMillis() + ".jpg";
                    path = Environment.getExternalStorageDirectory()
                            + "/prd/image/" + filename;

                    try {
                        FileOutputStream outputStream = new FileOutputStream(new File(path));
                        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }

        if (path == null) {

            return;
        }

        Log.e("tag", path);

        if (requestCode != 300) {

            cropPhoto(Uri.fromFile(new File(path)));
        } else {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            iv_tu.setImageBitmap(BitmapFactory.decodeFile(path, options));

        }


    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 300);
    }

    private void showToast(String text) {
        if (mToast != null && !super.isFinishing()) {
            mToast.setText(text);
            mToast.show();
            return;
        }
        mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
