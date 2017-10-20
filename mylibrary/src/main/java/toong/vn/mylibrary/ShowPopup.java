package toong.vn.mylibrary;

import android.content.Context;
import android.support.v4.widget.PopupWindowCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by PhanVanLinh on 20/10/2017.
 * phanvanlinh.94vn@gmail.com
 */

public class ShowPopup {

    public static void showSimplePopupWindow(Context context, View view) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = layoutInflater.inflate(R.layout.popup_layout_2, null);

        final PopupWindow popupWindow = new PopupWindow(popupView);
        popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(false);
        PopupWindowCompat.setWindowLayoutType(popupWindow, WindowManager.LayoutParams.TYPE_TOAST);
        popupWindow.showAsDropDown(view, -220, 0);
    }
}
