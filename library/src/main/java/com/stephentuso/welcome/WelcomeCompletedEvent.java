package com.stephentuso.welcome;

/**
 * Created by stephentuso on 12/17/15.
 * Used with EventBus, this event is triggered when the welcome screen is completed or skipped
 */
public class WelcomeCompletedEvent {

    /**
     * The key of the welcome screen that called this event
     */
    public final String welcomeScreenKey;

    public WelcomeCompletedEvent(String key) {
        this.welcomeScreenKey = key;
    }

}
