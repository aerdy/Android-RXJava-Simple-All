package com.necisstudio.rxjava.event;

/**
 * Created by vim on 01/12/16.
 */

public class EventMessage {
    private EventMessage() {
    }

    public static class Message {
        public final String message;

        public Message(String message) {
            this.message = message;
        }
    }
}
