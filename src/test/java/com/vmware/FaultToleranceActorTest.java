package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Test;

public class FaultToleranceActorTest {

    @Test
    public void testAskActor() throws InterruptedException {
        ActorSystem system = ActorSystem.create("MySystem");

        ActorRef reporterActorRef = system.actorOf(
                Props.create(LaunchingActor.class),
                       "launchingactor"); //this will be stored in the url /user/reporteractor
        system.actorOf(
                Props.create(ParentActor.class),
                       "parentactor"); // this will stored in the url /user/askactor

        reporterActorRef.tell("go", reporterActorRef);

        Thread.sleep(15000);
        system.shutdown();
        system.awaitTermination();
    }
}
