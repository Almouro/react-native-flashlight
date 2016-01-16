package com.almouro.reactnativeflashlight;

import android.content.Context;
import android.util.AndroidException;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

class ReactNativeFlashlightModule extends ReactContextBaseJavaModule {
    private Context context;
    private FlashlightControl flashlight;

    public ReactNativeFlashlightModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.context = reactContext;
        flashlight = new FlashlightControl();
    }

    /**
     * @return the name of this module. This will be the name used to {@code require()} this module
     * from javascript.
     */
    @Override
    public String getName() {
        return "ReactNativeFlashlight";
    }

    @ReactMethod
    public void toggleFlashlight(Callback onSuccess, Callback onFailure) {
        try {
            flashlight.toggle();
        } catch(AndroidException e) {
            onFailure.invoke(e.getMessage());
            return;
        }
        onSuccess.invoke();
    }
}
