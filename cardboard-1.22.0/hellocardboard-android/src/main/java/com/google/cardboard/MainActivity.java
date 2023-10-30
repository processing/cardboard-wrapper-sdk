package com.google.cardboard;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.cardboard.CardboardWrapper;

public class MainActivity extends AppCompatActivity {
    static {
        System.loadLibrary("cardboard_jni");
    }
    private long nativeApp;
    CardboardWrapper cardboardWrapper= new CardboardWrapper();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        nativeApp= nativeOnCreate(getAssets());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cardboardWrapper.OnDestroy(nativeApp);
        nativeApp = 0;
    }

    private native long nativeOnCreate(AssetManager assetManager);
}
