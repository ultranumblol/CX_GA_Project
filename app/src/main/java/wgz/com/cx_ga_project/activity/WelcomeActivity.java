package wgz.com.cx_ga_project.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import wgz.com.cx_ga_project.R;
import wgz.com.cx_ga_project.base.BaseActivity;
import wgz.com.cx_ga_project.observable.AnimatorOnSubscribe;

import static rx.schedulers.Schedulers.io;

/**
 * Created by wgz on 2016/8/8.
 */

public class WelcomeActivity extends BaseActivity {
    @Bind(R.id.img_welcome)
    ImageView imgWelcome;

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        Animator animation = AnimatorInflater.loadAnimator(mContext, R.animator.welcome_animator);
        //observeOn() 指定的是它之后的操作所在的线程
        //subscribeOn() 作用于Observable对象
        //onCompleted() 和 onError() 二者是互斥的 调用一个就不会再调用另一个
        animation.setTarget(imgWelcome);
        Observable<Void> observable =  Observable.create(new AnimatorOnSubscribe(animation));
        observable.observeOn(io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        // TODO: 2016/8/8 上次登录距离现在多久
                        return true;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe();// TODO: 2016/8/8 判断用户token




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
