package com.vmware;

import akka.actor.UntypedActor;

public class AskActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message.equals("How do you feel today?")) {
           Thread.sleep(10000);
           getSender().tell("Fine", getSelf());
        }
    }
}
