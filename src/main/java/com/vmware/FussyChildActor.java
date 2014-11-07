package com.vmware;

import akka.actor.UntypedActor;

public class FussyChildActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
         if(message.equals("illegal_argument")) {
             throw new IllegalArgumentException("I want candy!");
         } else if (message.equals("arithmetic")) {
             throw new ArithmeticException("I hate math!");
         } else if (message.equals("null")) {
             throw new NullPointerException("I don't know where my sister is");
         } else if (message.equals("class")) {
             throw new ClassNotFoundException("I don't want to go to school!");
         } else {
             unhandled(message);
         }
    }
}
