android - smooth stopping of scrolling in viewPager
===================================================

[Source](http://stackoverflow.com/questions/11544510/android-smooth-stopping-of-scrolling-in-viewpager/ "Source")

this problem is quite hard to describe.

basically , the viewpager on the play store (aka the android market) and on the official launcher of android works in a different way than the one found in the support library for this case , and i wish to get the same UI experience as there for scrolling between pages.

the situation is like this: when you touch the viewpager to scroll between the pages (and scroll a bit ), and then you stop touching and quickly touch again , i get a different behavior :

on the play store (and the official launcher) the scrolling animation stops and is kept in sync with the touch .
on the support library the viewpager just stops the animation and jumps to the page that it was about to go to , no matter where you touched it . there
how do i achieve the same functionality as the one on the play store and the official launcher?

## Answer ##

The [Android Playground](http://code.google.com/p/android-playground/ "android playground repo") project has this smooth stopping scroll you are talking about.

To be more specific, check [this](http://code.google.com/p/android-playground/source/browse/trunk/SwipeyTabsSample/src/net/peterkuterna/android/apps/swipeytabs/SwipeyTabs.java "view pager code") custom "View Pager" class.

