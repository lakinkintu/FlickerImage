package com.flicker.image.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class UtilityConstant {
    public static final String BASE_URL = "https://api.flickr.com/services/rest/";
    public static final String API_KEY = "b9c21c8f71665ab0ea065e9aca8ba8d1";
    public static final String METHOD = "?method=flickr.photos.search";
    public static final String FORMAT = "json";
    public static final int NOJSONCALLBACK = 1;
    public static final int PAGE = 1;
    public static final int PER_PAGE = 10;
    public static final boolean ISLOADING = true;
    public static final String PREVIOUS_TAG = null;


    public static void hideKeyBoard(View view, Context context) {
        InputMethodManager in = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void showMessage(Context context,String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
