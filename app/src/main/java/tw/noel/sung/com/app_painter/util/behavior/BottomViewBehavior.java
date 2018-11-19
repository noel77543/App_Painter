package tw.noel.sung.com.app_painter.util.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by noel on 2018/6/16.
 */

public class BottomViewBehavior extends CoordinatorLayout.Behavior<View> {

    private final int ANIMATION_DURATION = 300;
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    //控件距離coordinatorLayout底部距離
    private float viewY;
    //動畫是否在進行
    private boolean isAnimate;

    public BottomViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    //-----------------

    /**
     * 有嵌套滑動到來了，問下該Behavior是否接受嵌套滑動
     *
     * @param coordinatorLayout 當前的CoordinatorLayout
     * @param child             該Behavior對應的View
     * @param directTargetChild 我的理解是在CoordinateLayout下作為父View,而該View的子類是Tager的那個View,也就是Target的父View),因為我測試用ViewPager包裹了RecycleView後該參數返回Viewpager,如果沒有包裹參數返回的是RecycleView
     * @param target            具體嵌套滑動的那個子類
     * @param nestedScrollAxes  支持嵌套滾動軸。水平方向，垂直方向，或者不指定
     * @return 是否接受該嵌套滑動
     */
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //獲取控件距離父佈局（coordinatorLayout）底部距離
            viewY = coordinatorLayout.getHeight() - child.getY();
        }

        //ViewCompat是一個兼容類,在android5.0之前的API為了實現新的效果
        //避免出錯使用ViewCompat.xxxx方法可以解決出現低版本錯誤的問題

        //判斷是否豎直滾動
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //--------

    /**
     * @param coordinatorLayout 此行為與關聯的視圖的父級CoordinatorLayout
     * @param child             該Behavior對應的View
     * @param target            具體嵌套滑動的那個子類
     * @param dx                水平方向嵌套滑動的子View想要變化的距離
     * @param dy                垂直方向嵌套滑動的子View想要變化的距離
     * @param consumed          這個參數要我們在實現這個函數的時候指定，回頭告訴子View當前父View消耗的距離 consumed[0] 水平消耗的距離，consumed[1] 垂直消耗的距離 好讓子view做出相應的調整
     */
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        //dy大於0是向上滾動 小於0是向下滾動 數值拉大一點 避免敏感度過高
        if (dy > 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dy < 0 && !isAnimate && child.getVisibility() == View.INVISIBLE) {
            show(child);
        }
    }

    //-----------------

    /***
     *  隱藏
     * @param view
     */
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);

        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.INVISIBLE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
    //-----------------

    /***
     *  顯示
     * @param view
     */
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(ANIMATION_DURATION);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}