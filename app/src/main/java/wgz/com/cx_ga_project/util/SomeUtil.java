package wgz.com.cx_ga_project.util;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import wgz.com.cx_ga_project.R;

/**
 * Created by wgz on 2016/8/5.
 */

public class SomeUtil {
    public static Snackbar showSnackBar(View view, String message) {
        Snackbar snackbar=Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
        return snackbar;
    }
    public static void showNetworkErrorSnackBar(final Context context, View view, String message, String action) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction(action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        context.startActivity(intent);
                    }
                })
                .show();

    }

    public static void checkHttpException(Context mContext, Throwable mThrowable, View mRootView) {
        String snack_action_to_setting = "设置";
        if ((mThrowable instanceof UnknownHostException)) {
            String snack_message_net_error = "网络错误，请检查网络";
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_net_error, snack_action_to_setting);
        }  else if (mThrowable instanceof SocketTimeoutException) {
            String snack_message_time_out = "连接超时，请检查网络";
           showNetworkErrorSnackBar(mContext, mRootView, snack_message_time_out, snack_action_to_setting);
        } else if (mThrowable instanceof ConnectException) {
            String snack_message_net_error = "网络错误，请检查网络";
            showNetworkErrorSnackBar(mContext, mRootView, snack_message_net_error, snack_action_to_setting);
        } else {
            String snack_message_unknown_error ="未知错误";
            showSnackBar(mRootView,snack_message_unknown_error);
        }
    }

}
