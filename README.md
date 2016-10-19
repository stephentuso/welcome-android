Welcome
=======

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Welcome-blue.svg?style=flat)](http://android-arsenal.com/details/1/3610) [![Download](https://api.bintray.com/packages/stephentuso/maven/welcome/images/download.svg)](https://bintray.com/stephentuso/maven/welcome/_latestVersion) [![Build Status](https://travis-ci.org/stephentuso/welcome-android.svg?branch=master)](https://travis-ci.org/stephentuso/welcome-android) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/f88fea4256a24130959e16d5c30578ce)](https://www.codacy.com/app/tusodev/welcome-android?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=stephentuso/welcome-android&amp;utm_campaign=Badge_Grade)

An easy to use and customizable welcome screen for Android apps.

![Sample video](https://raw.githubusercontent.com/stephentuso/welcome-android/master/media/sample-video.gif)

Look in the [sample](https://github.com/stephentuso/welcome-android/blob/master/sample/src/main/java/com/stephentuso/welcomeexample/SampleWelcomeActivity.java) to see how the above welcome screen is created.

**Features**

-	Fully customizable
-	RTL support
-	Ability to use built in layouts or custom fragments
-	Built in layouts support all screen sizes and orientations

Please open a new [issue](https://github.com/stephentuso/welcome-android/issues) if you find a bug or have a problem.

[Javadoc](http://stephentuso.github.io/welcome-android/javadoc/)

[Changelog/Releases](https://github.com/stephentuso/welcome-android/releases)

Demo
====

A demo app is available on Google play:

<a href='https://play.google.com/store/apps/details?id=com.stephentuso.welcomeexample&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img width='240' alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png'/></a>

The source code is in the [sample module](https://github.com/stephentuso/welcome-android/blob/master/sample/src/main/).

Contributing
============

Feel free to open a PR to add a feature or fix a bug, all contributions are welcome. Please read the [contribution notes](https://github.com/stephentuso/welcome-android/CONTRIBUTING.md).

All new development takes place on the [dev branch](https://github.com/stephentuso/welcome-android/tree/dev).

Table of Contents
=================

-	[Adding to your project](#adding-to-your-project)
-	[Basic Usage](#basic-usage)
	-	[Extend WelcomeActivity](#extend-welcomeactivity)
	-	[Show the welcome screen](#show-the-welcome-screen)
-	[Skipping/Back button behavior](#skippingback-button-behavior)
-	[Included pages](#included-pages)
	-	[Title page](#title-page)
	-	[Basic page](#basic-page)
	-	[Parallax page](#parallax-page)
	-	[Full screen parallax page](#full-screen-parallax-page)
-	[Custom pages](#custom-pages)
	-	[Custom Done Button](#custom-done-button)
-	[Styling](#styling)
	-	[Themes](#themes)
	-	[Styles](#styles)
-	[Welcome screen keys](#welcome-screen-keys)
-	[Results](#results)
-	[Animations](#animations)
-	[License](#license)

Adding to your project
======================

This library is available through jCenter.

Gradle:

```groovy
compile 'com.stephentuso:welcome:0.7.3'
```

If you use proguard, add the following to your proguard rules

```
-keepclassmembers class * extends com.stephentuso.welcome.ui.WelcomeActivity {
    public static java.lang.String welcomeKey();
}
```

Basic Usage
===========

Extend WelcomeActivity
----------------------

To create a welcome screen, add a class to your project that extends `WelcomeActivity` and add it to AndroidManifest:

```xml
<activity android:name=".MyWelcomeActivity"
    android:theme="@style/WelcomeScreenTheme"/>
```

*You must set the theme in the manifest as shown above if you want swipeToDismiss to show the activity beneath. Note that the manifest theme will be overridden and have no effect on the other styles of the welcome screen (that will probably change in 1.0).*

Override the Activity's `configuration()` method. You can use `WelcomeScreenBuilder` to easily set it up, for example:

```java
@Override
protected WelcomeScreenConfiguration configuration() {
    return new WelcomeScreenBuilder(this)
            .theme(R.style.WelcomeScreenTheme_Light)
            .defaultBackgroundColor(R.color.background)
            .titlePage(R.drawable.logo, "Title")
            .basicPage(R.drawable.photo1, "Header", "More text.", R.color.red)
            .basicPage(R.drawable.photo2, "Lorem ipsum", "dolor sit amet.")
            .swipeToDismiss(true)
            .build();
}
```

You do not need to override `onCreate` or call `setContentView`.

*Note: Default typeface methods and defaultBackgroundColor() need to be called before adding pages.*

Show the welcome screen
-----------------------

Welcome screens are started with `WelcomeScreenHelper`. `onSaveInstanceState` is needed to be sure only one instance of the welcome screen is started. Add the following to the Activity you want to show the welcome screen before (probably your launcher activity):

```java
WelcomeScreenHelper welcomeScreen;

@Override
protected void onCreate(Bundle savedInstanceState) {
    ...
    welcomeScreen = new WelcomeScreenHelper(this, MyWelcomeActivity.class);
    welcomeScreen.show(savedInstanceState);
    ...
}

@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    welcomeScreen.onSaveInstanceState(outState);
}
```

If you have issues with the buttons/indicator being covered by the nav bar, use one of the .SolidNavigation welcome screen themes.

To force the welcome screen to be shown, for example, to let the user view it again when a button is pressed, create a `WelcomeScreenHelper` as shown above and call `.forceShow()`.

Skipping/Back button behavior
=============================

By default, the welcome screen can be skipped, and pressing the back button will navigate to the previous page or close (skip) the welcome screen if on the first page. This can be changed with `WelcomeScreenBuilder.canSkip()`, `backButtonSkips()` (only applies if `canSkip` is true), and `backButtonNavigatesPages()`. If you disable skipping, the welcome screen will not be stored as completed when it closes.

If you want to require users to navigate through the welcome screen before using the app, call `canSkip(false)` and close your app if the welcome screen's result is `RESULT_CANCELED`.

See [Results](https://github.com/stephentuso/welcome-android#results) below for how to respond if a welcome screen is canceled.

Included pages
==============

All methods shown in this section are part of `WelcomeScreenBuilder`. See the [javadoc](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/WelcomeScreenBuilder.html) for more info on each.

Title page
----------

A page with an image and a title. A parallax effect can be applied to the image.

[Javadoc](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/WelcomeScreenBuilder.html#titlePage-int-java.lang.String-)

```java
titlePage(int resId, String title)
titlePage(int resId, String title, int colorResId)
titlePage(int resId, String title, int colorResId, boolean showParallaxAnim)
titlePage(int resId, String title, int colorResId, boolean showParallaxAnim, String titleTypefacePath)
```

Basic page
----------

A page with an image, heading, and description. A parallax effect can be applied to the image.

[Javadoc](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/WelcomeScreenBuilder.html#basicPage-int-java.lang.String-java.lang.String-)

```java
basicPage(int resId, String title, String description)
basicPage(int resId, String title, String description, int colorResId)
basicPage(int drawableId, String title, String description, int colorResId, boolean showParallaxAnim)
basicPage(int drawableId, String title, String description, int colorResId, boolean showParallaxAnim,
    String headerTypefacePath, String descriptionTypefacePath)
```

Parallax page
-------------

Similar to the basic page, but instead of an image you can supply a layout that will have a parallax effect applied to it. The speed at which the layout's children move is determined by their position in the layout, the first will move the slowest and the last will move the fastest.

[Javadoc](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/WelcomeScreenBuilder.html#parallaxPage-int-java.lang.String-java.lang.String-)

```java
parallaxPage(int resId, String title, String description)
parallaxPage(int resId, String title, String description, int colorResId)
parallaxPage(int resId, String title, String description, int colorResId, float startParallaxFactor,
    float endParallaxFactor)
parallaxPage(int resId, String title, String description, int colorResId, float startParallaxFactor,
    float endParallaxFactor, String headerTypefacePath, String descriptionTypefacePath)
```

Full screen parallax page
-------------------------

Applies a parallax effect in the same way the normal parallax page does, but the layout you provide fills the whole fragment, and there isn't a header or description.

[Javadoc](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/WelcomeScreenBuilder.html#fullScreenParallaxPage-int-)

```java
fullScreenParallaxPage(int resId)
fullScreenParallaxPage(int resId, int colorResId)
fullScreenParallaxPage(int resId, int colorResId, float startParallaxFactor, float endParallaxFactor)
```

Custom pages
============

You can add your own fragments to the welcome screen with `WelcomeScreenBuilder.page()`:

```java
@Override
protected WelcomeScreenConfiguration configuration() {
    return new WelcomeScreenBuilder(this)
            ...
            .page(new WelcomeFragmentHolder() {
                @Override
                protected Fragment fragment() {
                    return new YourFragmentHere();
                }
            }, R.color.background-color)
            ...
}
```

See [animations](https://github.com/stephentuso/welcome-android#animations) below for adding animations to custom fragments.

### Custom Done Button

If you want to use a button in a custom fragment instead of the default done button, call `useCustomDoneButton(true)` on the builder and `new WelcomeScreenFinisher(MyFragment.this).finish()` in the button's `OnClickListener`.

Styling
=======

Themes
------

The provided themes are listed below.

Transparent status/navigation on API 19+. Content does not flow under status bar:

-	`WelcomeSceenTheme` - The default theme. For use with dark backgrounds; the text, indicator, and buttons are light colored.
-	`WelcomeScreenTheme.Light` - For use with light backgrounds; the text, indicator, and buttons are dark colored.

Transparent status bar, solid navigation bar on API 19+. Content does not flow under status bar:

-	`WelcomeScreenTheme.SolidNavigation`
-	`WelcomeScreenTheme.Light.SolidNavigation`

Transparent status bar, solid navigation bar on API 19+. Content flows under status bar:

-	`WelcomeScreenTheme.SolidNavigation.UnderStatusBar`
-	`WelcomeScreenTheme.Light.SolidNavigation.UnderStatusBar`

Styles
------

Typefaces and a few other things (animations, button visibility) have to be set with `WelcomeScreenBuilder`, but everything else that is customizable can be changed with styles.

You can add styles as shown below. Optional items are in square brackets. Apply your theme to a welcome screen with `WelcomeScreenBuilder.theme(R.style.YourThemeNameHere)`.

```xml
<style name="CustomWelcomeScreenTheme" parent="SEE THEMES ABOVE">

    <!-- Color of button text and titles/headings (in built in fragments)
        By default, this is also the color of the done/next button -->
    <item name="android:textColorPrimary">color</item>
    <!-- Color of other text
        By default, this is used for the skip button text color -->
    <item name="android:textColorSecondary">color</item>

    <!-- ** Button styles ** -->
    <!-- Background is applied to all buttons,
        to change a specific button background use the individual button styles -->
    <item name="welcomeButtonBackground">drawable</item>
    <!-- Done/skip button text -->
    <item name="welcomeButtonSkipText">string</item>
    <item name="welcomeButtonDoneText">string</item>
    <!-- Individual button styles -->
    <item name="welcomeButtonSkipStyle">@style/MyButtonSkip</item>
    <item name="welcomeButtonNextStyle">@style/MyButtonNext</item>
    <item name="welcomeButtonDoneStyle">@style/MyButtonDone</item>

    <!-- The drawable or color to fade to if swipeToDismiss is enabled -->
    <item name="android:windowBackground">drawable|color</item>

    <item name="welcomeIndicatorStyle">@style/MyWelcomeIndicator</item>
    <item name="welcomeDividerStyle">@style/MyWelcomeScreenDivider</item>

    <item name="welcomeNormalTextStyle">@style/MyNormalText</item>
    <item name="welcomeLargeTextStyle">@style/MyLargeText</item>
    <item name="welcomeTitleTextStyle">@style/MyTitleText</item>

    <!-- Add the following if you want to show the action bar.
        Use WelcomeScreenBuilder.showActionBarBackButton(true) to show
        the back button. -->
    <item name="windowActionBar">true</item>
    <item name="windowNoTitle">false</item>
</style>

<style name="MyWelcomeIndicator" parent="WelcomeScreenPageIndicator[.Light]">
    <item name="indicatorColor">color</item>
    <item name="currentPageColor">color</item>
    <item name="animated">true|false</item>
</style>

<!-- Use this to change the next button's image/color
    To support RTL, add this in values-ldrtl/styles with an image facing left -->
<style name="MyButtonNext" parent="WelcomeScreenButton.Next">
    <item name="android:src">drawable</item>
    <item name="android:tint">color</item>
</style>

<style name="MyButtonSkip|MyButtonDone" parent="WelcomeScreenButton(.Skip|.Done)">
    <item name="android:textColor">color</item>
</style>

<!-- A divider that is directly above the buttons/indicator.
The background color is transparent by default -->
<style name="MyWelcomeScreenDivider" parent="WelcomeScreenDivider[.Dark|.Light]">
    <item name="android:background">drawable|color</item>
    <item name="android:layout_height">dimen</item>
</style>

<!-- The following can apply to any of the three text styles -->
<style name="MyText" parent="WelcomeScreenText[.Large|.Title][.Centered]">
    <!-- Add any properties that can be applied to a TextView -->
</style>

```

Welcome screen keys
===================

If you want to use multiple welcome screens (in different parts of your app) or have updated one and want to show it again, you can assign keys (Make sure they are unique!) to welcome screens by adding the following to your welcome screen Activity.

```java
public static String welcomeKey() {
    return "Your unique key";
}
```

**Note:** Only change this to a new value if you want everyone who has already used your app to see the welcome screen again! This key is used to determine whether or not to show the welcome screen.

Results
=======

You can listen for the result of a welcome screen in the Activity that started it by overriding `onActivityResult`:

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);


    if (requestCode == WelcomeScreenHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
        // The key of the welcome screen is in the Intent
        String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

        if (resultCode == RESULT_OK) {
            // Code here will run if the welcome screen was completed
        } else {
            // Code here will run if the welcome screen was canceled
            // In most cases you'll want to call finish() here
        }

    }

}
```

One use for this is making sure users see the whole welcome screen before using your app - disable skipping and then close your main activity when the welcome screen is canceled.

Animations
==========

Animations that play as pages are scrolled can be added to your custom fragments by implementing [WelcomeScreenPage.OnChangeListener](http://stephentuso.github.io/welcome-android/javadoc/com/stephentuso/welcome/ui/WelcomeScreenPage.OnChangeListener.html). As an example, a fade effect is shown below.

```java
@Override
public void onScrolled(int pageIndex, float offset, int offsetPixels) {
    if (Build.VERSION.SDK_INT >= 11 && imageView != null) {
        imageView.setAlpha(1-Math.abs(offset));
    }
}
```

To add parallax effects similar to the included parallax page, use [WelcomeUtils.applyParallaxEffect()](http://stephentuso.github.io/welcome-android/javadoc/index.html?overview-summary.html) in `onScrolled`. For example:

```java
@Override
public void onScrolled(int pageIndex, float offset, int offsetPixels) {
    if (parallaxLayout != null)
        WelcomeUtils.applyParallaxEffect(parallaxLayout, false, offsetPixels, 0.3f, 0.2f);
}
```

License
=======

```
Copyright 2015-2016 Stephen Tuso

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
