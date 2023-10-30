package com.google.cardboard;

import android.content.res.AssetManager;

import androidx.appcompat.app.AppCompatActivity;

public class CardboardWrapper extends AppCompatActivity {

    static {
        System.loadLibrary("cardboard_jni");
    }
    public void OnDestroy(long nativeApp){
        nativeOnDestroy(nativeApp);
    }
    public void OnSurfaceCreated(long nativeApp){
        nativeOnSurfaceCreated(nativeApp);
    }
    public void OnPause(long nativeApp){
        nativeOnPause(nativeApp);
    }
    public void OnResume(long nativeApp){
        nativeOnResume(nativeApp);
    }
    public void SwitchViewer(long nativeApp){
        nativeSwitchViewer(nativeApp);
    }
    public void OnDrawFrame(long nativeApp){
        nativeOnDrawFrame(nativeApp);
    }
    public void OnTriggerEvent(long nativeApp){
        nativeOnTriggerEvent(nativeApp);
    }
    public void SetScreenParams(long nativeApp, int width, int height){
        nativeSetScreenParams(nativeApp,width,height);
    }

//    private native long nativeOnCreate(AssetManager assetManager);

    private native void nativeOnDestroy(long nativeApp);

    private native void nativeOnSurfaceCreated(long nativeApp);

    private native void nativeOnDrawFrame(long nativeApp);

    private native void nativeOnTriggerEvent(long nativeApp);

    private native void nativeOnPause(long nativeApp);

    private native void nativeOnResume(long nativeApp);

    private native void nativeSetScreenParams(long nativeApp, int width, int height);

    private native void nativeSwitchViewer(long nativeApp);
}
