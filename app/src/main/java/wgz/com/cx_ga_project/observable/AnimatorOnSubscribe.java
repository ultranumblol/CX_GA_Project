package wgz.com.cx_ga_project.observable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import rx.Observable;
import rx.Subscriber;
import wgz.datatom.com.utillibrary.util.LogUtil;

import static com.jakewharton.rxbinding.internal.Preconditions.checkUiThread;

/**
 * Created by wgz on 2016/8/8.
 */

public class AnimatorOnSubscribe implements Observable.OnSubscribe<Void> {
    final Animator animator;

    //构造器传入Animator
    public AnimatorOnSubscribe(Animator animator) {
        this.animator = animator;
    }


    @Override
    public void call(final Subscriber<? super Void> subscriber) {
        checkUiThread();
        AnimatorListenerAdapter animatorListenerAdapter = new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                subscriber.onNext(null);
                LogUtil.e("onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                subscriber.onCompleted();
                LogUtil.e("onAnimationEnd");
            }
        };

        animator.addListener(animatorListenerAdapter);
        animator.start();//先绑定监听器再开始

    }
}
