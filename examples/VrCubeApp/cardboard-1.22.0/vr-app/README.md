# Using Wrapper Functions to build a Sample App

By now, you must be familiar with Cardboard NDK and how my wrapper function works. For further clarity, I've created this sample app to demonstrate it's use in creating applications.

## Prerequisites

Before you begin, make sure you have the following prerequisites:

- Know how JNI works.( Though the Wrapper class saves you the hassle of creating your own JNI bridge, it's a necessity for understanding the internal working of the app. Recommended sources would be [Java for Engineers](https://www.youtube.com/watch?v=CwwFp4qhQyw&list=PLWchVAowvRxCc4N5F5RTJnDV150Vf7ouf) and [Android NDK Documentation](https://developer.android.com/ndk).)
- An understanding of how my wrapper functions work would help in customising your own app and creating your own Wrapper functions as and when necesssary.[here](https://github.com/ranaaditya/processing-cardboard-android-poc/blob/example-app/cardboard-1.22.0/tutorials.md)
- Some basic understanding about VR apps and their working.

## About the Sample App
The app serves as an example to create basic apps using the Cardboard SDK. The Wrapper is used to call native functions from the JNI, which acts as a bridge between the Java Wrapper and the C++ code. The VRActivity uses the wrapper class to create functions that are necessary for the life cycle of the VRApp (such as OnResume,OnPause etc). The majority of the work is done in the hello_cardboard_app.cc file, including setting up the Eye, Headtransform, Assigning view to GLSurfaceView etc.

### Some Tips:
The java function only acts as an outer shell, setting up a medium for the c++ code to work. To make any edits in the internal working, you would need to edit the C++ Code itself, including the JNI and the accompaning C++ and header files.

## Conclusion
The wrapper functions can be used to simplify the creation of the apps using Cardboard SDK, but the knowledge of JNI is a must to create a fully functional app.

Happy Coding! 
