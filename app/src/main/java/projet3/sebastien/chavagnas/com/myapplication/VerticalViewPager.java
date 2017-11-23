package projet3.sebastien.chavagnas.com.myapplication;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;


public class VerticalViewPager extends ViewPager {
    private static final String TAG = "VerticalViewPager";
    private static final boolean DEBUG = true;

    private float mLastMotionX;
    private float mLastMotionY;
    private float mTouchSlop;
    private boolean mVerticalDrag;

    // Vertical transit page transformer
    private final ViewPager.PageTransformer mPageTransformer = new ViewPager.PageTransformer() {
        @Override
        public void transformPage(View view, float position) {
            final int pageWidth = view.getWidth();
            final int pageHeight = view.getHeight();

            if (position <= 1) {
                view.setAlpha(1);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // set Y position to swipe in from top
                float yPosition = position * pageHeight;
                view.setTranslationY(-yPosition);
            }
        }
    };

    public VerticalViewPager(Context context) {
        super(context, null);
    }

    public VerticalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        init();
    }

    private void init() {
        // Make page transit vertical
        setPageTransformer(true, mPageTransformer);
        // Get rid of the over scroll drawing that happens on the left and right (the ripple)
        setOverScrollMode(View.OVER_SCROLL_NEVER);
    }


    @Override
    public boolean performClick(){
        super.performClick();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        // Show x & y of MotionEvent ev
        final float x = ev.getX();
        final float y = ev.getY();
        if (DEBUG) Log.v(TAG, "onTouchEvent " + x + ", " + y);

        //
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                // User is touching the screen, we intercept x & y
                performClick();
                mLastMotionX = x;
                mLastMotionY = y;
                return super.onTouchEvent(ev) && verticalDrag(ev);
            }
            case MotionEvent.ACTION_MOVE: {
                // User is moving the finger on screen, we intercept the movement
                performClick();
                final float xDiff = Math.abs(x - mLastMotionX);
                final float yDiff = Math.abs(y - mLastMotionY);
                if (!mVerticalDrag) {
                    // ViewPager uses xDiff to start drag/swipe to left/right
                    // We use here yDiff to manage swiping up and down
                    if (yDiff > mTouchSlop && yDiff > xDiff) { //Swiping up and down
                        mVerticalDrag = true;
                    }
                }
                if (mVerticalDrag) {
                    return verticalDrag(ev);
                }
            }
            case MotionEvent.ACTION_UP: {
                // User is lifting the finger off the screen, we set the TouchEvent to false
                performClick();
                if (mVerticalDrag) {
                    mVerticalDrag = false;
                    return verticalDrag(ev);
                }
            }
        }
        // Set both flags to false in case user lifted finger in the parent view pager
        mVerticalDrag = false;
        return false;
    }


    private boolean verticalDrag(MotionEvent ev) {
        // Switching x & y results to simulate Vertical motion
        final float y = ev.getX();
        final float x = ev.getY();
        ev.setLocation(x, y);
        return super.onTouchEvent(ev);
    }
}
