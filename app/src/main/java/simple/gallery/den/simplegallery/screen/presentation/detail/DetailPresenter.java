package simple.gallery.den.simplegallery.screen.presentation.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import simple.gallery.den.simplegallery.R;
import simple.gallery.den.simplegallery.screen.main.BaseMainPresenter;


public class DetailPresenter extends BaseMainPresenter<DetailView> {

    private static final int QUALITY = 90;
    @Inject
    Context context;

    @Inject
    public DetailPresenter() {
    }

    @Override
    public void onStart() {
        getView().showPhoto();
    }

    @Override
    public void onStop() {

    }

    public void sharePhoto(ImageView imageView) {
        Uri bmpUri = getLocalBitmapUri(imageView);
        if (bmpUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_photo)));
        } else {
            Toast.makeText(context, context.getString(R.string.share_error), Toast.LENGTH_SHORT).show();
        }
    }

    private Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable) {
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, QUALITY, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
