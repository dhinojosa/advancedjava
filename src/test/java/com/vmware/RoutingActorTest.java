package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

public class RoutingActorTest {

    @Test
    public void testRouterActor() throws InterruptedException {
        Config config = ConfigFactory.load("routing-system");

        ActorSystem system = ActorSystem.create("MySystem", config);

        ActorRef actorRef = system.actorOf(FromConfig.getInstance()
                .props(Props.create(SimpleActor.class)),
                "my-router");

        actorRef.tell("go", system.deadLetters());
        actorRef.tell("go1", system.deadLetters());
        actorRef.tell("go2", system.deadLetters());
        actorRef.tell("go3", system.deadLetters());
        actorRef.tell("go4", system.deadLetters());
        actorRef.tell("go5", system.deadLetters());
        actorRef.tell("go6", system.deadLetters());
        actorRef.tell("go7", system.deadLetters());
        actorRef.tell("go8", system.deadLetters());
        actorRef.tell("go9", system.deadLetters());
        actorRef.tell("go10", system.deadLetters());

        Thread.sleep(15000);
        system.shutdown();
        system.awaitTermination();
    }
}
