package app.codelabs.forum.helpers;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 11 May 2020, 13:49
 */

public class ActivityUtils {
    public static void hideKeyboard(Activity activity, View view) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
