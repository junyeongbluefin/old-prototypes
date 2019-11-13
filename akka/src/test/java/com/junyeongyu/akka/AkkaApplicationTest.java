package com.junyeongyu.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.TestKit;
import com.junyeongyu.akka.actors.Device;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import scala.concurrent.duration.Duration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AkkaApplicationTest {

    private static ActorSystem system;

    @BeforeAll
    static void setup() {
        system = ActorSystem.create();
    }

    @Test
    void main() {
//        new AkkaApplication().main(new String[]{});

    }

    @Test
    void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(new Device.ReadTemperature(42L), probe.testActor());
        Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(42L, response.requestId);
        assertEquals(Optional.empty(), response.value);
    }

    @AfterAll
    public static void teardown() {
        TestKit.shutdownActorSystem(system, Duration.Zero(), false);
        system = null;
    }
}