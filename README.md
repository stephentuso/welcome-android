Welcome
=======

[![GitHub version](https://badge.fury.io/gh/stephentuso%2Fwelcome-android.svg)](https://badge.fury.io/gh/stephentuso%2Fwelcome-android) [![Build Status](https://travis-ci.org/stephentuso/welcome-android.svg?branch=master)](https://travis-ci.org/stephentuso/welcome-android)

An easy to use and customizable welcome screen for Android apps.

![Sample video](https://raw.githubusercontent.com/stephentuso/welcome-android/master/media/sample-video.gif)

*Look in the [sample](https://github.com/stephentuso/welcome-android/blob/master/sample/src/main/java/com/stephentuso/welcomeexample/MyWelcomeActivity.java) to see how the above welcome screen is defined.*

-	Easily customizable
-	RTL support
-	Ability to use built in layouts or custom fragments

Adding to your project
----------------------

This library is available through jCenter.

Gradle:

```
compile 'com.stephentuso:welcome:0.2.3'
```

If you use proguard, add the following to your proguard rules

```
-keepclassmembers class * extends com.stephentuso.welcome.ui.WelcomeActivity {
    public static String welcomeKey();
}

-keepclassmembers class ** {
    public void onEvent*(***);
}
```

Usage
-----

### Basic

A basic welcome screen can be added in two steps:

#### Extend WelcomeActivity

To create a welcome screen, add a class to your project that extends `WelcomeActivity` (don't forget to add it to your AndroidManifest), and override the `configuration()` method. You can use `WelcomeScreenBuilder` to easily set it up:

```
@Override
protected WelcomeScreenConfiguration configuration() {
    return new WelcomeScreenBuilder(this)
            .defaultBackgroundColor(R.color.background)
            .titlePage(R.drawable.logo, "Title")
            .page(new YourCustomFragmentHere())
            .basicPage(R.drawable.photo1, "Header", "More text.", R.color.red)
            .basicPage(R.drawable.photo2, "Lorem ipsum", "dolor sit amet.")
            .swipeToDismiss(true)
            .build();
}
```

#### Show the welcome screen

Welcome screens are started with `WelcomeScreenHelper`. Put the class of your welcome screen in the second argument of the constructor.

```
new WelcomeScreenHelper(this, MyWelcomeActivity.class).show();
```

You can call this from your launcher activity's onCreate or onStart. This will only show the welcome screen if the user hasn't completed it yet.

### More options

#### Welcome screen keys

You can assign keys (Make sure they are unique!) to welcome screens by adding the following to a class that extends `WelcomeActivity`.

```
public static String welcomeKey() {
        return "Your unique key";
    }
```

Only change this to a new value if you want everyone who has already used your app to see the welcome screen again! This key is used to determine whether or not to show the welcome screen. This could be useful if you use multiple welcome screens, or if you have updated one and want to show it again.

#### Styling

You can add styles as shown below. Optional items are in square brackets.

```
<style name="CustomWelcomeScreenTheme" parent="WelcomeScreenTheme[.Light]">

    <!-- Color of button text and titles/headings (in built in fragments) -->
    <item name="android:textColorPrimary">color</item>
    <!-- Color of other text -->
    <item name="android:textColorSecondary">color</item>

    <item name="welcomeIndicatorStyle">@style/MyWelcomeIndicator</item>
    <item name="welcomeButtonNextStyle">@style/MyButtonNext</item>
    <item name="welcomeButtonBackground">drawable</item>
    <item name="welcomeDividerStyle">@style/MyWelcomeScreenDivider</item>
    <item name="welcomeNormalTextStyle">@style/MyNormalText</item>
    <item name="welcomeLargeTextStyle">@style/MyLargeText</item>
    <item name="welcomeTitleTextStyle">@style/MyTitleText</item>
</style>

<style name="MyWelcomeIndicator" parent="WelcomeScreenPageIndicator[.Light]">
<item name="indicatorColor">color</item>
    <item name="currentPageColor">color</item>
    <item name="animated">true|false</item>
</style>

<!-- Use this to change the image used for the next button.
If you want to support RTL, add this in values-ldrtl/styles with a different image -->
<style name="MyButtonNext" parent="WelcomeScreenNextButton[.Dark|.Light]">
    <item name="android:src">drawable</item>
</style>

<!-- A divider that is directly above the buttons/indicator.
The background color is transparent by default -->
<style name="MyWelcomeScreenDivider" parent="WelcomeScreenDivider[.Dark|.Light]">
    <item name="android:background">drawable|color</item>
    <item name="android:layout_height">dimen</item>
</style>

<!-- The following can apply to any of the three text styles -->
<style name="MyText" parent="WelcomeScreenText[.Large[.Title]]">
    <!-- Add any properties that can be applied to a TextView -->
</style>

```

Apply your theme to a welcome screen in its `configuration()` by calling `WelcomeScreenBuilder.theme(int)`, passing your theme as the parameter.

For now, there isn't a way to change the typefaces in the supplied fragments, you will have to use your own fragments to do that.

#### Events (WIP)

You can listen for the result of a welcome screen in the activity you start it from.

-	Call `WelcomeScreenHelper.register(this)` in `onCreate`
-	Call `WelcomeScreenHelper.unregister(this)` in `onDestroy`
-	Add the two `onEvent` methods as shown below

```
@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    WelcomeScreenHelper.register(this);
    new WelcomeScreenHelper(this, MyWelcomeActivity.class).show();
    ...
}

@Override
protected void onDestroy() {
    ...
    WelcomeScreenHelper.unregister(this);
}

public void onEvent(WelcomeCompletedEvent event) {
    // Code here will run when the welcome screen has completed or has been skipped. Access the key of the welcome screen with event.welcomeScreenKey
}

public void onEvent(WelcomeFailedEvent event) {
    // Code here will run when the welcome screen can't be skipped and the back button was pressed on the first page. Access the key of the welcome screen with event.welcomeScreenKey
}
```

The failure event is mostly meant to be used when you don't want users to use the app without going through the welcome screen. If you wanted that, you could close the app in the failure method.

Please note that these will not be called if resources are low and the activity they are in gets destroyed in the background.

Todo
----

-	Make `swipeToDismiss` fade to transparency (showing the activity beneath) rather than white
-	Complete PreferenceWelcomeFragment

License
-------

> Copyright 2015 Stephen Tuso
>
> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
>
> http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
