package wgz.com.cx_ga_project.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.app;
import wgz.com.cx_ga_project.base.APIservice;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.bean.UserBean;
import wgz.com.cx_ga_project.util.SomeUtil;
import wgz.datatom.com.utillibrary.util.LogUtil;
import wgz.datatom.com.utillibrary.util.NetUtil;

/**
 * 登陆
 * wgz
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.actv_username)
    AutoCompleteTextView actvUsername;
    @Bind(R.id.edit_password)
    EditText editPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.progress_login)
    ProgressBar progressLogin;
    @Bind(R.id.scroll_login_form)
    ScrollView scrollLoginForm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        RxView.clicks(btnLogin)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        attemptLogin();
                    }
                });
        addUsernameAutoComplete();
    }

    private void addUsernameAutoComplete() {

        // TODO: 2016/8/5  系统读入内容帮助用户输入用户名

    }

    private void attemptLogin() {
        actvUsername.setError(null);
        editPassword.setError(null);
        String username = actvUsername.getText().toString();
        String password = editPassword.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            editPassword.setError("请输入密码！");
            focusView = editPassword;
            cancel = true;
        }
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            editPassword.setError("密码太短了！");
            focusView = editPassword;
            cancel = true;
        }
        // Check for a valid username address.
        if (TextUtils.isEmpty(username)) {
            actvUsername.setError("请输入警员编号！");
            focusView = actvUsername;
            cancel = true;
        }
        //所有的检查完成 判断是否能开始联网 还是弹出提示
        if (cancel) {
            focusView.requestFocus();
        }else {
            // TODO: 2016/8/5 登录功能还差联网
            httpLogin(username, password);
        }

    }

    private void httpLogin(String username, String password) {
        app.apiService.UserLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        showProgress(true);
                    }
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);
                        LogUtil.e("error:"+e.toString());
                        SomeUtil.checkHttpException(getApplicationContext(),e,scrollLoginForm);
                    }
                    @Override
                    public void onNext(UserBean userBean) {
                        showProgress(false);

                        SomeUtil.showSnackBar(scrollLoginForm,"登录成功！").setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                                finish();
                            }
                        });
                        saveUserInfo();
                    }
                });
    }
    private void saveUserInfo() {
        // TODO: 2016/8/5 存储用户信息还没加
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            progressLogin.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            progressLogin.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressLogin.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            progressLogin.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        }
    }
}

