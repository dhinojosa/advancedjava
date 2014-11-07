package com.vmware;

import akka.actor.*;

public class SimpleActor extends UntypedActor {

    public SimpleActor() {
        System.out.println("Created Actor");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        System.out.format("Message Received in %s: %s\n", Thread.currentThread().getName(), message);
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
