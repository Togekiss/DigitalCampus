package com.example.sanfe.digitalcampus.logic.dataManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Marta on 30/05/2016.
 */
public class BitmapManager {

    public static Bitmap resizeBitmap(String path) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        if (options.outHeight > 1024 || options.outWidth > 1024) {
            if (options.outHeight > 2048 || options.outWidth > 2048) {
                if (options.outHeight > 8192 || options.outWidth > 8192) {
                    options.inSampleSize = 16;
                }
                else options.inSampleSize = 4;
            } else options.inSampleSize = 2;
        }

        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }
    public static Bitmap resizeForFullScreen(String path) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);


        if (options.outHeight > 2048 || options.outWidth > 2048) {
            if (options.outHeight > 8192 || options.outWidth > 8192) {
                options.inSampleSize = 4;
            }
            else options.inSampleSize = 2;
        }


        options.inJustDecodeBounds = false;
        Bitmap bm = BitmapFactory.decodeFile(path, options);

        return bm;
    }

}
