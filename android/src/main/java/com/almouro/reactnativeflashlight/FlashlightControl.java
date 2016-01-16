package com.almouro.reactnativeflashlight;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.AndroidException;

import java.io.IOException;
import java.util.List;

class FlashlightControl {

    private boolean isLightOn;
    private Camera camera;
    private Camera.Parameters cameraParameters;

    public void turnOn() throws AndroidException {
        camera = Camera.open();
        if (!this.hasFlashlight()) throw new AndroidException("Flashlight not available");

        cameraParameters = camera.getParameters();
        cameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(cameraParameters);

        SurfaceTexture texture = new SurfaceTexture(0);
        try {
            camera.setPreviewTexture(texture);
        } catch (IOException ex) {
            // Ignore
        }
        camera.startPreview();
        isLightOn = true;
    }

    public void turnOff() throws AndroidException {
        if (!this.hasFlashlight()) throw new AndroidException("Flashlight not available");

        cameraParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(cameraParameters);
        camera.stopPreview();
        camera.release();
        isLightOn = false;
    }

    public boolean hasFlashlight() {
        if (camera == null) return false;

        final List<String> supportedFlashModes = camera.getParameters().getSupportedFlashModes();
        return supportedFlashModes != null
                && supportedFlashModes.contains(Camera.Parameters.FLASH_MODE_TORCH);
    }

    public void toggle() throws AndroidException {
        if (isLightOn) turnOff();
        else           turnOn();
    }

    public boolean isLightOn() {
        return isLightOn;
    }
}
