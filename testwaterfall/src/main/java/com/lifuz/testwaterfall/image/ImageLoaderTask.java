package com.lifuz.testwaterfall.image;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lifuz.testwaterfall.bean.TaskParam;

import java.lang.ref.WeakReference;

/**
 * 作者：李富 on 2015/8/10.
 * 邮箱：lifuzz@163.com
 */
public class ImageLoaderTask extends AsyncTask<TaskParam, Void, Bitmap> {

    private TaskParam param;
    //若引用，保存的对象可以被GC回收掉
    private WeakReference<ImageView> imageViewWeakReference;

    public ImageLoaderTask(ImageView imageView) {

        imageViewWeakReference = new WeakReference<ImageView>(imageView);

    }

    @Override
    protected Bitmap doInBackground(TaskParam... params) {
        //回去TaskParam对象

        param = params[0];

        return loadImageFile(param.getFileName(), param.getAssetManager());
    }

    private Bitmap loadImageFile(final String fileName, final AssetManager manager) {

        Log.i("tag", fileName);
        Log.i("tag", param.getAssetManager().toString());

        Bitmap bmp = BitmapCache.getInstance().getBitmap(fileName, param.getAssetManager());
        Log.i("tag", bmp.toString());

        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewWeakReference != null) {

            ImageView imageView = imageViewWeakReference.get();

            if (imageView != null) {

                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();

                layoutParams.height = param.getItemHeight();
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                imageView.setLayoutParams(layoutParams);
                imageView.setImageBitmap(bitmap);
            }

        }

    }
}
