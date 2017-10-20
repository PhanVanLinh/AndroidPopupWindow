package com.toong.androidpopupwindow;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_show_popup_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                //showWindowManager();

                showSimplePopupWindow();
//                showDialog();
            }
        });
    }

    public void showWindowManager() {
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                // Shrink the window to wrap the content rather than filling the screen
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
                // Display it on top of other application windows, but only for the current user
                WindowManager.LayoutParams.TYPE_TOAST,
                // Don't let it grab the input focus
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                // Make the underlying application window visible through any transparent parts
                PixelFormat.TRANSLUCENT);

        // Define the position of the window within the screen
        p.gravity = Gravity.TOP | Gravity.RIGHT;
        p.x = 0;
        p.y = 100;

        final WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_layout, null);
        windowManager.addView(popupView, p);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                windowManager.removeView(popupView);
            }
        }, 2000);
    }

    public void showPopupWindowWithEditText() {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_layout, null);
        EditText editText = popupView.findViewById(R.id.edt);

        final PopupWindow popupWindow = new PopupWindow(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setFocusable(false);
        editText.requestFocus();
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        popupView.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popupWindow.showAsDropDown(findViewById(R.id.button_show_popup_window));
    }

    public void showSimplePopupWindow() {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_layout_2, null);

        final PopupWindow popupWindow = new PopupWindow(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        popupView.findViewById(R.id.button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
//        PopupWindowCompat.setWindowLayoutType(popupWindow, WindowManager.LayoutParams.TYPE_SYSTEM_ERROR);
        popupWindow.showAtLocation(getWindow().getDecorView().getRootView(), Gravity.NO_GRAVITY, 0, 60);
    }

    public void showDialog() {
        new AlertDialog.Builder(this).setTitle("s").show();
    }

    private void startSecondActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,
                        SecondActivity.class));
            }
        }, 1000);
    }
}
