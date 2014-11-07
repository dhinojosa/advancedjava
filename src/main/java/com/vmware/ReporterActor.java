package com.vmware;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.dispatch.OnSuccess;
import scala.concurrent.Future;

import static akka.pattern.Patterns.ask;

public class ReporterActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
       if (message.equals("go")) {

           ActorSelection selection =
                   context().actorSelection("/user/askactor");
           Future<Object> future = ask(selection, "How do you feel today?", 9000);
           future.onSuccess(new OnSuccess<Object>() {
               @Override
               public void onSuccess(Object result) throws Throwable {

               }
           }, context().dispatcher());
x


       }
    }
}
