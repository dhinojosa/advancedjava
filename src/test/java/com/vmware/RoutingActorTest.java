package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import akka.routing.RouterConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.junit.Test;

public class RoutingActorTest {

    @Test
    public void testRouterActor() throws InterruptedException {

        ActorSystem system = ActorSystem.create("MySystem");

        ActorRef actorRef = system.actorOf(FromConfig.getInstance()
                        .props(Props.create(SimpleActor.class)), "my-router");

        for (int i = 0; i < 1000; i++) {
            actorRef.tell("go" + i, system.deadLetters());
        }

        Thread.sleep(15000);
        system.shutdown();
        system.awaitTermination();
    }
}
