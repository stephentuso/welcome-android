Welcome
=======

[![GitHub version](https://badge.fury.io/gh/stephentuso%2Fwelcome-android.svg)](https://badge.fury.io/gh/stephentuso%2Fwelcome-android)

An easy to use and customizable welcome screen for Android apps.

I would not recommend using this in production quite yet, it is early in development and will receive many changes over the next week or two.

This library is available through jCenter.

Gradle:

```
    compile 'com.stephentuso:welcome:0.2.0'
```

Usage
-----

A basic welcome screen can be added in two steps:

1.	Extend WelcomeActivity and implement `configuration()`
2.	Start the welcome screen

### Step 1

To create a welcome screen, add a class to your project that extends `WelcomeActivity` (don't forget to add it to your AndroidManifest), and override the `configuration()` method. You can use `WelcomeScreenBuilder` to easily set it up:

```
@Override
protected WelcomeScreenConfiguration configuration() {
    return new WelcomeScreenBuilder(this)
            .defaultBackgroundColor(R.color.background)
            .titlePage(R.drawable.logo, "Title")
            .basicPage(R.drawable.photo1, "Header", "More text.", R.color.red)
            .basicPage(R.drawable.photo2, "Lorem ipsum", "dolor sit amet.")
            .page(new YourCustomFragmentHere())
            .swipeToDismiss(true)
            .build();
}
```

### Step 2

Welcome screens are started with `WelcomeScreenHelper`. Put the class of your welcome screen in the second argument of the constructor.

```
new WelcomeScreenHelper(this, MyWelcomeActivity.class).show();
```

You can call this from your launcher activity's onCreate or onStart. This will only show the welcome screen if the user hasn't completed it yet.

To see a full implementation of this library, look in the sample folder. More documentation will be coming soon.

Todo
----

-	Make `swipeToDismiss` fade to transparency (showing the activity beneath) rather than white
-	Complete PreferenceWelcomeFragment
-	Add fade animation to button visibility changes (hide/show methods in WelcomeScreenViewWrapper)

License
-------

> Copyright 2015 Stephen Tuso
>
> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
>
> http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
