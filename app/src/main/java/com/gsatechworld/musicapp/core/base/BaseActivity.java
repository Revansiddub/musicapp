package com.gsatechworld.musicapp.core.base;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.gsatechworld.musicapp.R;

import static android.graphics.Color.TRANSPARENT;
import static android.view.Window.FEATURE_NO_TITLE;
import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;
import static com.google.android.material.snackbar.Snackbar.make;
import static java.util.Objects.requireNonNull;

public abstract class BaseActivity extends AppCompatActivity {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private Dialog dialog;

    /* ------------------------------------------------------------- *
     * Protected Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to hide the soft keyboard.
     *
     * @param activity the calling activity.
     */
    protected void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(INPUT_METHOD_SERVICE);

        /*Find the currently focused view, so we can grab the correct window token from it.*/
        View view = activity.getCurrentFocus();

        /*If no view currently has focus, create a new one, just so we can grab a window token
         from it*/
        if (view == null)
            view = new View(activity);

        requireNonNull(imm).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * This method is invoked to display soft keyboard.
     */
    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        requireNonNull(imm).toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /* ------------------------------------------------------------- *
     * Public Methods
     * ------------------------------------------------------------- */

    /**
     * This method is invoked to display loading indicator.
     */
    public void showLoadingIndicator() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(FEATURE_NO_TITLE);
        requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.setContentView(R.layout.layout_loading_indicator);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    /**
     * This method is invoked to hide loading indicator.
     */
    public void hideLoadingIndicator() {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
    }

    /**
     * This method is invoked to display a snackBar in the screen.
     *
     * @param context layout in which we want to display snack bar.
     * @param message message that we want to display in snack bar.
     */
    public void showSnackBar(Activity context, String message) {
        Snackbar snackbar = make(context.findViewById(android.R.id.content), message, LENGTH_LONG);
        View view = snackbar.getView();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        view.setLayoutParams(layoutParams);
        view.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        snackbar.show();
    }
}