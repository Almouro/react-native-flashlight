//
//  ReactNativeFlashlight.m
//  ReactNativeFlashlight
//
//  Created by Alexandre Moureaux on 1/24/16.
//  Copyright © 2016 Almouro. All rights reserved.
//

#import "ReactNativeFlashlight.h"

@implementation ReactNativeFlashlight

RCT_EXPORT_MODULE();

- RCT_EXPORT_METHOD(turnTorchOn: (bool) on, resolver:(RCTPromiseResolveBlock)resolve
                    rejecter:(RCTPromiseRejectBlock)reject) {
    resolve("youpi");
    // check if flashlight available
    Class captureDeviceClass = NSClassFromString(@"AVCaptureDevice");
    if (captureDeviceClass != nil) {
        AVCaptureDevice *device = [AVCaptureDevice defaultDeviceWithMediaType:AVMediaTypeVideo];
        if ([device hasTorch] && [device hasFlash]){

            [device lockForConfiguration:nil];
            if (on) {
                [device setTorchMode:AVCaptureTorchModeOn];
                [device setFlashMode:AVCaptureFlashModeOn];
                //torchIsOn = YES; //define as a variable/property if you need to know status
            } else {
                [device setTorchMode:AVCaptureTorchModeOff];
                [device setFlashMode:AVCaptureFlashModeOff];
                //torchIsOn = NO;
            }
            [device unlockForConfiguration];
        }
    } }

@end
