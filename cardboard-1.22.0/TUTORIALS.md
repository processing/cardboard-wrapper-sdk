# Using Wrapper Functions for Cardboard NDK

In this tutorial, we will learn how to use wrapper functions to simplify interaction with the Cardboard NDK (Native Development Kit) in an Android application. Wrapper functions encapsulate the native functionality, making it more accessible from the Java code.

## Prerequisites

Before you begin, make sure you have the following prerequisites:

- Android Studio or your preferred Android development environment.
- A basic understanding of Android app development.
- A project that uses the Cardboard NDK.
- A bit of awesomeness to vibe with this project :)

## Getting Familiar with the Cardboard NDK project
- The project has two main files:
---> cardboard-1.22.0
  |---> hellocardboard-android: Stores all the module level files such as CMakeLists, gradle(module level), Activities and Xml files.
  |---> sdk: Contains the cardboard SDK, and all the util and header files.
- Our wrapper function is present in hellocardboard-android/src/main/java/com.google.cardboard/CardboardWrapper.java
- The JNI library is located at src/main/jni. It has 5 files: hello_cardboard_app.cc, hello_cardboard_app.h, util.cc, util.h and hello_cardboard_jni.cc.
- We will edit hello_cardboard_jni.cc to add OnCreate function for our Activity later.
- With this you have a basic knowledge about the structure of the project, so let's have a look at the Wrapper class.

## More about the Wrapper
The wrapper has the following functions:

- **OnDestroy**
  - Calls the native `nativeOnDestroy` function to perform cleanup or resource release when an Android activity is destroyed.

- **OnSurfaceCreated**
  - Invokes the native `nativeOnSurfaceCreated` function, typically used for initializing OpenGL or rendering resources when the rendering surface is created.

- **OnPause**
  - Calls the native `nativeOnPause` function, which is used to pause or suspend any ongoing activity in the native code when the Android activity is paused.

- **OnResume**
  - Invokes the native `nativeOnResume` function to resume any activity in the native code when the Android activity is resumed.

- **SwitchViewer**
  - Calls the native `nativeSwitchViewer` function, which may be used to switch between different viewer modes or configurations in the native code.

- **OnDrawFrame**
  - Invokes the native `nativeOnDrawFrame` function, typically used for rendering a frame or updating the visual content when a new frame is to be drawn.

- **OnTriggerEvent**
  - Calls the native `nativeOnTriggerEvent` function, which may be used to handle trigger or interaction events, often related to user input.

- **SetScreenParams**
  - Invokes the native `nativeSetScreenParams` function with the provided width and height parameters, which is used to set screen dimensions or parameters in the native code, often for rendering purposes.

Now that we are familiar with the wrapper class, let's use it to create our own activity that uses Cardboard SdK to create VR apps.

## Creating a VRActivity

1. Create a Java class,(say MainActivity.java) in your module.
   ![image](https://github.com/ranaaditya/processing-cardboard-android-poc/assets/60355338/cbc081a3-a540-4b0f-b31a-ec29accbafdc)
2. Make sure this class extends AppCompatActivity, as that's what's recommended by google while using Cardboard SDk. Now, Import the CardboardWrapper.java class in your class.
   Using 'import com.google.cardboard.CardboardWrapper;'
3. Now let's load the cardboardJni library and create the native function for nativeOnCreate. The code should look something like this:
   ```
   package com.google.cardboard;
   
   import android.content.res.AssetManager;
   
   import androidx.appcompat.app.AppCompatActivity;
   import com.google.cardboard.CardboardWrapper;

   public class MainActivity extends AppCompatActivity {
       static {
           System.loadLibrary("cardboard_jni");
       }
   
   private native long nativeOnCreate(AssetManager assetManager);
   }
   ```
   Now, let's define the nativeOnCreate function in our JNI file(main/jni/hello_cardboard_jni.cc).
4. Inside hello_cardboard_jni.cc file, first let's define your own JNI_METHOD which can be used to define native functions for your new Activity.
   This can be achieved using the following code:
   ```
   #define JNI_METHOD_Main_Activity(return_type, method_name) \
     JNIEXPORT return_type JNICALL                      \
         Java_com_google_cardboard_MainActivity_##method_name
   ```
   You can name your method in a way similar to `JNI_METHOD_Main_Activity` and add the path to your activity like this `Java_com_google_cardboard_MainActivity_##method_name`.

   Now define your OnCreate method inside extern "C" {}. Similar to this:
  ```
  JNI_METHOD_Main_Activity(jlong, nativeOnCreate)
  (JNIEnv* /*env*/, jobject obj, jobject asset_mgr) {
    return jptr(new ndk_hello_cardboard::HelloCardboardApp(javaVm, obj, asset_mgr));
  }
  ```
  With this, you have successfully created your own JNI method which you can use in the newly created (MainActivity) class. A similar approach can be used to create new JNI functions for other classes as well.
5. Now we need two objects in MainActivity class 'nativeApp' and 'cardboardWrapper' that can be used to call the nativeOnCreate function and use the wrapper classes.
  ```
    public class MainActivity extends AppCompatActivity {
        static {
            System.loadLibrary("cardboard_jni");
        }
        private long nativeApp;
        CardboardWrapper cardboardWrapper= new CardboardWrapper();
        
        private native long nativeOnCreate(AssetManager assetManager);
    }
  ```
6. Now let's call the native function for nativeOnCreate and a wrapper function. This can be done as follows:
```
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
```
With this, we are now familiar with:
- Creating native functions and adding them to the JNI for cardboard sdk.
- Using the Wrapper Class in our new Activity and calling it's functions.

Proper use of the wrapper functions can be seen in VrActivity.java, which uses the nativeOnCreate method and Wrapper functions to create a sample VR App.
## Conclusion
Using wrapper functions can simplify the interaction with the Cardboard NDK in your Android application. It provides a cleaner and more maintainable interface for your Java code to work with the native functionality.

This tutorial demonstrates the basic steps for creating native functions and using wrapper functions. You can extend this concept to encapsulate more native functionality as needed.

Happy Coding! 
