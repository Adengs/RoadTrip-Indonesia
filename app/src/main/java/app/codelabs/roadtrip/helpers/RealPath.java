package app.codelabs.roadtrip.helpers;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 11:36
 */
public class RealPath {
    private ByteArrayOutputStream byteArrayOutputStream;
    private String resultPath;
    private Context context;
    private Cursor cursor;
    private int indexPath;
    private File tempDirectory,temp;
    private RealPath(Context context) {
        this.context = context;
    }
    public String prefix;
    public static RealPath with(Context context){
        return new RealPath(context);
    }
    public File getTempFileImage(ByteArrayOutputStream byteArrayOutputStream, String prefix) {
        this.prefix = prefix;
        this.byteArrayOutputStream = byteArrayOutputStream;
        return new File(getRealPathUri(getUriImage()));
    }
    public String getRealPathUri(Uri uri) {
        resultPath = null;
        cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            resultPath = uri.getPath();
        } else {
            if (cursor.moveToFirst()) {
                indexPath = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                resultPath = cursor.getString(indexPath);
            }
            cursor.close();
        }
        return resultPath;
    }
    private Uri getUriImage() {
        return Uri.fromFile(getFileImage());
    }
    private File getFileImage() {
        tempDirectory = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "Temp");
        if (!tempDirectory.exists()) {
            if (!tempDirectory.mkdirs()) {
                return null;
            }
        }
        temp = new File(tempDirectory.getPath() + File.separator + "temp" + prefix + ".jpg");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();

            MediaScannerConnection.scanFile(context, new String[]{temp.getPath()}, new String[]{"image/jpg"}, null);
        } catch (IOException e) {
        }
        return temp;
    }

    public void deleteTempFileImage(File temp) {
        temp.delete();
        MediaScannerConnection.scanFile(context, new String[]{temp.getPath()}, null, null);
    }

    public Bitmap adjustBitmap(Bitmap bitmap, Uri uri) {
        Bitmap adjustedBitmap = bitmap;
        try {
            ExifInterface exifInterface = new ExifInterface(getRealPathUri(uri));
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            int degrees = getDegrees(orientation);

            Matrix matrix = new Matrix();
            if (degrees != 0f) {
                matrix.preRotate(degrees);
            }
            adjustedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
        }
        return adjustedBitmap;
    }

    public Bitmap resizeBitmap(Uri uri) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        return imageLoader.loadImageSync(uri.toString());
    }
    private int getDegrees(int orientation) {
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

}
