package com.vmware;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class SimpleActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(context().system(), this);

    public SimpleActor() {
        System.out.println("Created Actor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("Message Received: {}", message);
    }

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef ref = system.actorOf(Props.create(SimpleActor.class), "charlize");
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());

        ActorSelection charlize = system.actorSelection("/user/charlize");
        charlize.tell("I love you", system.deadLetters());

        system.shutdown();
        system.awaitTermination();
    }
}
