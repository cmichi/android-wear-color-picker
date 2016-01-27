package de.uulm.mhci.colorpicker;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle state) {
        ColorPicker circularColorPicker = new ColorPicker(this);
        setContentView(circularColorPicker);

        super.onCreate(state);
    }
}