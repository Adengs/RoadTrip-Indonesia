package app.codelabs.fevci.helpers;

import android.util.Patterns;
import android.widget.EditText;

/**
 * Created by edinofri
 * tukangbasic@gmail.com
 * 08 Apr 2020, 17:01
 */
public class Validator {
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isFieldEmpty(EditText editText) {
        return editText.getText().toString().isEmpty();
    }

    public static boolean isFieldMin(EditText editText, int min) {
        return editText.getText().toString().length() >= min;
    }

    public static boolean isFieldMax(EditText editText, int max) {
        return editText.getText().toString().length() <= max;
    }

}
