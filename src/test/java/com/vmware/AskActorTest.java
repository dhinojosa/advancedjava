package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.junit.Test;

public class AskActorTest {

    @Test
    public void testAskActor() throws InterruptedException {
        ActorSystem system = ActorSystem.create("MySystem");

        ActorRef reporterActorRef = system.actorOf(
                Props.create(ReporterActor.class),
                       "reporteractor"); //this will be stored in the url /user/reporteractor
        ActorRef askActorRef = system.actorOf(
                Props.create(AskActor.class),
                       "askactor"); // this will stored in the url /user/askactor

        reporterActorRef.tell("go", system.deadLetters());

        Thread.sleep(15000);
        system.shutdown();
        system.awaitTermination();
    }
}
