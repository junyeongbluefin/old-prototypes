package com.junyeongyu.akka;


import java.io.IOException;

import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import com.junyeongyu.akka.actors.IotSupervisor;

public class AkkaApplication {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("iot-system");

        try {
            // Create top level supervisor
            ActorRef supervisor = system.actorOf(IotSupervisor.props(), "iot-supervisor");

            System.out.println("Press ENTER to exit the system");

            System.in.read();
        } finally {
            system.terminate();
        }
    }

}

