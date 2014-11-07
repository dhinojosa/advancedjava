package com.vmware;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AskActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(context().system(), this);

    Config regularConfig = ConfigFactory.load();

    @Override
    public void onReceive(Object message) throws Exception {
        log.debug(regularConfig.getString("akka.my-message"));
        if (message.equals("How do you feel today?")) {
           Thread.sleep(10000);
           log.debug("Ready to answer...");
           getSender().tell("Fine", getSelf());
        } else {
            unhandled(message);
        }
    }
}