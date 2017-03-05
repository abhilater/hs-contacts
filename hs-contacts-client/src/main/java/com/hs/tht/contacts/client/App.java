package com.hs.tht.contacts.client;

import com.hs.tht.contacts.client.command.AppProcessor;

/**
 * Entry point main class of the application.
 *
 * @author abhishek
 * @version 1.0, 5/3/17
 */
public class App {

    /**
     * It starts here...
     * Main function for the App.
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            new AppProcessor().startApp();
        } catch (Throwable t) {
            System.err.print(t.getMessage());
            System.exit(1);
        }
    }
}
