package de.uulm.mhci.colorpicker.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.view.View;

public class RingView extends View {

    private float centerX;
    private float centerY;
    private float minCircle;
    private float maxCircle;
    private float rotation = 0;

    // colors (starting from centerX, centerY)
    public static final int colorRing = 0xFF444444;

    // the outer circle stroke is very hard to see on the Moto 360
    public static final int colorOuterCircleStroke = colorRing;

    // the color gradient
    private final int[] colorGradient = new int[]{
            0xFFFF0000, 0xFFFF00FF, 0xFF0000FF, 0xFF00FFFF, 0xFF00FF00,
            0xFFFFFF00, 0xFFFF0000
    };

    public RingView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        centerX = getMeasuredWidth() / 2f;
        centerY = getMeasuredHeight() / 2f;

        super.onLayout(changed, l, t, r, b);
    }

    private int interpretColor(int colors[], float unit) {
        if (unit <= 0) {
            return colors[0];
        }
        if (unit >= 1) {
            return colors[colors.length - 1];
        }

        float p = unit * (colors.length - 1);
        int i = (int) p;
        p -= i;

        int c0 = colors[i];
        int c1 = colors[i + 1];
        int a = ave(Color.alpha(c0), Color.alpha(c1), p);
        int r = ave(Color.red(c0), Color.red(c1), p);
        int g = ave(Color.green(c0), Color.green(c1), p);
        int b = ave(Color.blue(c0), Color.blue(c1), p);

        return Color.argb(a, r, g, b);
    }

    private int ave(int s, int d, float p) {
        return s + java.lang.Math.round(p * (d - s));
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        float radius = Math.min(getMeasuredWidth(), getMeasuredHeight()) / 2f;

        Paint paint = new Paint();

        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.rotate(-90.0f, centerX, centerY);
        Shader s = new SweepGradient(centerX, centerY, colorGradient, null);

        paint.setShader(s);

        canvas.drawCircle(centerX, centerY, maxCircle * radius, paint);

        paint.setShader(null);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1.0f);
        paint.setColor(colorOuterCircleStroke);
        canvas.drawCircle(centerX, centerY, maxCircle * radius, paint);

        paint.setStyle(Paint.Style.FILL);

        Rect bounds = new Rect();

        canvas.rotate(-rotation, centerX, centerY);

        float unit = (float) ((Math.toRadians(Math.abs(rotation))) / (2.0f * (float) Math.PI));
        if (unit < 0) {
            unit += 1;
        }

        float r = getRot();
        if (r < 0.0f) r = 360.0f + r;

        r = 360.0f - r;

        paint.setColor(interpretColor(colorGradient, r / 360.0f));

        canvas.drawCircle(centerX, centerY, minCircle * radius, paint);

        paint.setStyle(Paint.Style.FILL);

        canvas.restore();
        super.onDraw(canvas);
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
     * Setting the rotation angle
     *
     * @param rot : rotation angle as float
     */
    public void setRot(float rot) {
        rotation = rot;
        this.invalidate();
    }

    /**
     * Get current rotation angle
     *
     * @return returns the current rotation angle
     */
    public float getRot() {
        return rotation;
    }
}
