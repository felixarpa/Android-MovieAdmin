package idi.felixjulen.movieadmin.domain.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileManager {

    private static Context context;
    private static FileManager ourInstance;

    public static FileManager getInstance(Context mContext) {
        context = mContext;
        if (ourInstance == null) {
            ourInstance = new FileManager();
        }
        return ourInstance;
    }

    public String saveToInternalStorage(String name, Bitmap bitmapImage) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(name, Context.MODE_PRIVATE);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return name;
    }

    public Bitmap loadImageFromStorage(String name, Integer alternative) {
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(name);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            return BitmapFactory.decodeResource(context.getResources(), alternative);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap resourceToBitmap(Integer alternative) {
        return BitmapFactory.decodeResource(context.getResources(), alternative);
    }
}
