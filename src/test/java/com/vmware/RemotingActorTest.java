package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import org.junit.Test;

public class RemotingActorTest {

    @Test
    public void testRemoteActor() throws InterruptedException {

        ActorSystem system = ActorSystem.create("MySystem");

        system.actorOf(Props.create(SimpleActor.class), "my-router");

        ActorSelection selection = system.actorSelection
                ("akka.tcp://MySystem@192.168.34.216:2556/user/my-router");

        selection.tell("Howdy", system.deadLetters());

        Thread.sleep(1500000);
        system.shutdown();
        system.awaitTermination();
    }
}
