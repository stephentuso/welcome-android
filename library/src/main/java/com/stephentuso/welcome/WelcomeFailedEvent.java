package com.stephentuso.welcome;

/**
 * Created by stephentuso on 12/17/15.
 */
public class WelcomeFailedEvent {

    public final String welcomeScreenKey;

    public WelcomeFailedEvent(String key) {
        this.welcomeScreenKey = key;
    }

}
