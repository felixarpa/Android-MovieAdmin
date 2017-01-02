package idi.felixjulen.movieadmin.presentation.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SwipeViewPager extends ViewPager {

    private boolean enabledSwipe;

    public SwipeViewPager(Context context) {
        super(context);
        enabledSwipe = false;
    }

    public SwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        enabledSwipe = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabledSwipe) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabledSwipe) {
            return super.onInterceptTouchEvent(event);
        }
        return false;
    }

    public void enable() {
        this.enabledSwipe = true;
    }

    public void disable() {
        this.enabledSwipe = false;
    }
}
