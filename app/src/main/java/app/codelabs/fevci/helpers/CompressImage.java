package app.codelabs.fevci.helpers;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 11:35
 */
public class CompressImage extends AsyncTask<Void, Integer, Void> {
    final int QUALITIY = 100;
    final int SIZE_FILE = 1024;

    private int compressQuality;
    private int targetCompress;
    private int min;
    private int progress;

    private Bitmap bitmap;
    private ByteArrayOutputStream byteStream;
    private OnImageCompressed callback;

    public CompressImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public CompressImage(Bitmap bitmap, OnImageCompressed callback) {
        this.bitmap = bitmap;
        this.callback = callback;
    }

    public CompressImage setOnImageCompressed(OnImageCompressed callback) {
        this.callback = callback;
        return this;
    }

    @Override
    protected void onPreExecute() {
        byteStream = new ByteArrayOutputStream();
        compressQuality = QUALITIY;
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, byteStream);

        targetCompress = SIZE_FILE;
        min = byteStream.size() / SIZE_FILE;
        progress = min - targetCompress;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if (progress > 0) {
            while (progress > 0) {
                try {
                    byteStream.flush();
                    byteStream.reset();
                } catch (IOException ignored) {

                }

                bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, byteStream);
                compressQuality -= 1;

                if (compressQuality == 0) {
                    break;
                }

                progress = byteStream.size() / SIZE_FILE - targetCompress;
                if (progress < 0) {
                    progress = 0;
                }
                publishProgress((int) (((float) (min - progress) / (float) min) * 100));
            }
        } else {
            publishProgress(100);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            callback.onCompressed(byteStream, bitmap);
        }
    }

    public interface OnImageCompressed {
        void onCompressed(ByteArrayOutputStream byteArrayOutputStream, Bitmap bitmap);
    }
}