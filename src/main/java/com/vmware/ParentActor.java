package com.vmware;

import akka.actor.*;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class ParentActor extends UntypedActor {

    private static SupervisorStrategy strategy =
            new OneForOneStrategy(10, Duration.create(1, TimeUnit.MINUTES),
                    new Function<Throwable, SupervisorStrategy.Directive>() {
                        @Override
                        public SupervisorStrategy.Directive apply(Throwable t) {
                            if (t instanceof ArithmeticException) {
                                return SupervisorStrategy.resume();
                            } else if (t instanceof NullPointerException) {
                                return SupervisorStrategy.restart();
                            } else if (t instanceof IllegalArgumentException) {
                                return SupervisorStrategy.stop();
                            } else {
                                return SupervisorStrategy.escalate();
                            }
                        }
                    });

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Props) {
            Props props = (Props) message;
            //Thread.sleep(1000); //Ilya
            ActorRef actorRef = getContext().actorOf(props);
            getSender().tell(actorRef, getSelf());
        } else {
            unhandled(message);
        }
    }
}
