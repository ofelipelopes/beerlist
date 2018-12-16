package teste.great.felipelopes.punk_api.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import teste.great.felipelopes.punk_api.utils.NetworkUtil;

public class ImageLoader {

    public synchronized static String imageDownload(final Context context, final String url) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir()).append("/").append(NetworkUtil.hashIt(url)).append(".png");
        final String path = sb.toString();
        Picasso.with(context)
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    File file = new File(path);
                                    file.createNewFile();
                                    FileOutputStream ostream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, ostream);
                                    ostream.flush();
                                    ostream.close();
                                } catch (IOException e) {
                                    Log.e("IOException", e.getLocalizedMessage());
                                }
                            }
                        }).start();
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        return path;
    }
}
