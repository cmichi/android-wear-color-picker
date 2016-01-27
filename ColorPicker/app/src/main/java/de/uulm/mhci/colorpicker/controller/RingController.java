package de.uulm.mhci.colorpicker.controller;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import de.uulm.mhci.colorpicker.view.RingView;

public class RingController implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnLayoutChangeListener,
        View.OnTouchListener {

    private float centerX;
    private float centerY;
    private float minCircle;
    private float maxCircle;
    protected RingView ringView;

    private GestureDetectorCompat mDetector;

    private int direction = 0;

    private float startAngle;
    private boolean isDragging;

    public RingController(Context context, RingView ringView, DisplayMetrics displayMetrics) {
        this.ringView = ringView;
        mDetector = new GestureDetectorCompat(context, this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        if (isInRingArea(touchX, touchY)) {
            Log.i("touchEvent", "was in ring area");
        } else {
            // this could be used for acknowledging a selection
            Log.i("touchEvent", "was outside of ring area");
        }

        mDetector.onTouchEvent(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                startAngle = touchAngle(touchX, touchY);
                isDragging = isInRingArea(touchX, touchY);


                break;
            case MotionEvent.ACTION_MOVE:
                if (isDragging && isInRingArea(touchX, touchY)) {
                    float touchAngle = touchAngle(touchX, touchY);
                    float deltaAngle = (360 + touchAngle - startAngle + 180) % 360 - 180;

                    if (direction == 0) {
                        direction = (int) Math.signum(deltaAngle);
                    }

                    ringView.setRotation((ringView.getRotation() + deltaAngle) % 360.0f);
                    ringView.setRot((ringView.getRot() + deltaAngle) % 360.0f);

                    startAngle += deltaAngle;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isDragging = false;
                direction = 0;
                break;
        }
        return true;
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        centerX = v.getMeasuredWidth() / 2f;
        centerY = v.getMeasuredHeight() / 2f;
    }

    /**
     * Define the draggable disc area with relative circle radius
     * based on min(width, height) dimension (0 = center, 1 = border)
     *
     * @param radius1 : internal or external circle radius
     * @param radius2 : internal or external circle radius
     */
    public void setDiscArea(float radius1, float radius2) {
        radius1 = Math.max(0, Math.min(1, radius1));
        radius2 = Math.max(0, Math.min(1, radius2));
        minCircle = Math.min(radius1, radius2);
        maxCircle = Math.max(radius1, radius2);
    }

    /**
     * Check if touch event is located in the ring area
     *
     * @param touchX : X position of the finger in this view
     * @param touchY : Y position of the finger in this view
     */
    private boolean isInRingArea(float touchX, float touchY) {
        float innerRadius = 220;

        float dX2 = (float) Math.pow(centerX - touchX, 2);
        float dY2 = (float) Math.pow(centerY - touchY, 2);
        float distToCenter = (float) Math.sqrt(dX2 + dY2);
        float baseDist = Math.min(centerX, centerY);

        if (distToCenter >= innerRadius / 2) return true;
        else return false;
    }

    /**
     * Compute a touch angle in degrees from center
     * North = 0, East = 90, West = -90, South = +/-180
     *
     * @param touchX : X position of the finger in this view
     * @param touchY : Y position of the finger in this view
     * @return angle
     */
    private float touchAngle(float touchX, float touchY) {
        float dX = touchX - centerX;
        float dY = centerY - touchY;
        return (float) (270 - Math.toDegrees(Math.atan2(dY, dX))) % 360 - 180;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        // Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        // Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}