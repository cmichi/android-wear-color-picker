package de.uulm.mhci.colorpicker;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;

import de.uulm.mhci.colorpicker.controller.RingController;
import de.uulm.mhci.colorpicker.view.RingView;

public class ColorPicker extends RelativeLayout {

    private RingView ringView;
    private RingController ringController;

    public ColorPicker(Context context) {
        super(context);

        ringView = new RingView(getContext());
        ringView.setDiscArea(.60f, 1.0f);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        ringController = new RingController(getContext(), ringView, displayMetrics);
        ringController.setDiscArea(.70f, 1.0f);
        setOnTouchListener(ringController);

        ringView.addOnLayoutChangeListener(ringController);

        addView(ringView, new RelativeLayout.LayoutParams(0, 0) {
            {
                width = MATCH_PARENT;
                height = MATCH_PARENT;
                addRule(RelativeLayout.CENTER_IN_PARENT);
            }
        });
    }
}