import React from 'react-native';

const ReactNativeFlashlight = React.NativeModules.ReactNativeFlashlight;

export default {
  toggleFlashlight: (onSuccess, onFailure) => {
    return ReactNativeFlashlight.turnTorchOn(true, onSuccess, onFailure);
  },
};
