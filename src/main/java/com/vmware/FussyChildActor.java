package com.vmware;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class FussyChildActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(context().system(), this);


    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        super.preRestart(reason, message);
        log.debug("pre-restarting");
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
        log.debug("post-restarting");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        log.debug("actor-stopped");
    }

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("path = {}, message = {}", getSender().path(), message.toString());
        if(message.equals("illegal_argument")) {
             log.debug("throwing an illegal argument exception");
             throw new IllegalArgumentException("I want candy!");
         } else if (message.equals("arithmetic")) {
             throw new ArithmeticException("I hate math!");
         } else if (message.equals("null")) {
             throw new NullPointerException("I don't know where my sister is");
         } else if (message.equals("class")) {
             throw new ClassNotFoundException("I don't want to go to school!");
         } else {
             unhandled(message);
         }
    }
}
