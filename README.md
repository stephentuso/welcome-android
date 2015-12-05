Welcome [![GitHub version](https://badge.fury.io/gh/stephentuso%2Fwelcome-android.svg)](https://badge.fury.io/gh/stephentuso%2Fwelcome-android)
===============================================================================================================================================

WIP

Note: Anyone who cloned this will need to make a fresh clone, I used [BFG](https://github.com/rtyley/bfg-repo-cleaner) to remove some unneeded files so all the commits are different from those in the original repository.

An easy to use and customizable welcome screen for Android apps.

I would not recommend using this in production quite yet, it is early in development and will receive many changes over the next week or two.

Usage
-----

This library is available through jCenter.

In your `build.gradle`:

```
dependencies {
    compile 'com.stephentuso:welcome:0.1.1'
}
```

More info coming soon, for now look in `sample` to see implementation

Todo
----

-	Find an alternative to using `BroadcastReceiver` for completed/failed events, otherwise figure out why using `LocalBroadcastManager` wouldn't work
-	Make `swipeToDismiss` fade to transparency (showing the activity beneath) rather than white

License
=======

> Copyright 2015 Stephen Tuso
>
> Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
>
> http://www.apache.org/licenses/LICENSE-2.0
>
> Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
