package com.juancoob.nanodegree.and.filmsdroid.util;


import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.juancoob.nanodegree.and.filmsdroid.R;

/**
 * Created by Juan Antonio Cobos Obrero on 19/02/18.
 */

public class ActivityUtils {

    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        if(fragmentManager != null && fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.commit();
        }
    }

    public static Uri getImageUri(String imagePath, Context context) {
        return Uri.parse(Constants.MOVIE_DB_BASE_IMAGE_URL).buildUpon()
                .appendEncodedPath(context.getResources().getString(R.string.default_image_size))
                .appendEncodedPath(imagePath)
                .build();
    }

}
