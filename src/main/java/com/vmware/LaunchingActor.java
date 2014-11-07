package com.vmware;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.Future;

import static akka.pattern.Patterns.ask;

public class LaunchingActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(context().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
       if (message.equals("go")) {
           ActorSelection selection =
                   context().actorSelection("/user/parentactor");

           Future<Object> future = ask(selection,
                               Props.create(FussyChildActor.class), 15000);

           future.onSuccess(new OnSuccess<Object>() {
               @Override
               public void onSuccess(Object result) throws Throwable {
                   log.debug("Successful result");
                   if (result instanceof ActorRef) {
                       ActorRef child = (ActorRef) result;
                       child.tell("null", self());
                   }
               }
           }, context().dispatcher());
       } else {
           unhandled(message);
       }
    }
}
