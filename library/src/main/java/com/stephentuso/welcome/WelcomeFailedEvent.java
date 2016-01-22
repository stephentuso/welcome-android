package com.stephentuso.welcome;

/**
 * Created by stephentuso on 12/17/15.
 * Used with EventBus, this event is triggered when the welcome screen can't be skipped
 * and the user closes it.
 */
public class WelcomeFailedEvent {

    /**
     * The key of the welcome screen that called this event
     */
    public final String welcomeScreenKey;

    public WelcomeFailedEvent(String key) {
        this.welcomeScreenKey = key;
    }

}
