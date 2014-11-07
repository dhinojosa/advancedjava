package com.vmware;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.Future;

import static akka.pattern.Patterns.ask;

public class ReporterActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(context().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
       if (message.equals("go")) {
           ActorSelection selection =
                   context().actorSelection("/user/askactor");
           Future<Object> future = ask(selection,
                   "How do you feel today?", 15000);
           log.debug("If we see this under 5 sec. it does not block");
           future.onSuccess(new OnSuccess<Object>() {
               @Override
               public void onSuccess(Object result) throws Throwable {
                   log.info("Received Result: {}", result);
               }
           }, context().dispatcher());
       } else {
           unhandled(message);
       }
    }
}
