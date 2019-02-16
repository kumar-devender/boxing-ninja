package com.ninja.boxing;

import com.ninja.boxing.app.module.config.AppConfiguration;

/**
 * @author DEVENDER
 * Application starting point.
 *
 */
public final class BoxingNinjaApplication {
    private BoxingNinjaApplication() {
        
    }
    /**
     * Main Method.Entry point of application
     */
    public static void main(final String[] args){
        AppConfiguration.welcome();
    }
}
