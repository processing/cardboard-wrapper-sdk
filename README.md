# Wrapper Class for Cardboard SDk
The [Cardboard SDK](https://developers.google.com/cardboard/develop/c/quickstart) is the latest supported library by Google to support the creation of VR Apps on Android. Though, the addition of JNI complicates things for developers, creating a wrapper over the Native library can help facilitate VR development not only in the Processing Community but anyone who wants to use Cardboard SDK to create VR apps. 

## About the Wrapper
The aim was to create a wrapper class that could be used in Activities and Java classes to create a simple VR app without the hassle of creating a separate JNI Bridge for the native library.
The [CardboardWrapper](https://github.com/ranaaditya/processing-cardboard-android-poc/blob/example-app/cardboard-1.22.0/hellocardboard-android/src/main/java/com/google/cardboard/CardboardWrapper.java) helps us achieve this. To understand how it works, I recommend going the [documentation](https://github.com/ranaaditya/processing-cardboard-android-poc/blob/example-app/cardboard-1.22.0/TUTORIALS.md) and a [sample app](https://github.com/ranaaditya/processing-cardboard-android-poc/tree/example-app/examples/VrCubeApp/cardboard-1.22.0/vr-app) was created to better understand how this can be used to create simple VR apps.

## Future Prospects
This Wrapper can be used to create a library that can work along side Processing 4 to help create VR Apps using the Cardboard SDK. 
