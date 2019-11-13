package junyeong.yu.statemachine;

import junyeong.yu.statemachine.enums.ActionEvent;
import junyeong.yu.statemachine.enums.Status;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineBuilder.Builder;

import java.util.EnumSet;

public class StateMachineBuilder {
    public StateMachine<Status, ActionEvent> buildMachine() {
        Builder<Status, ActionEvent> builder = org.springframework.statemachine.config.StateMachineBuilder.builder();

        try {
            builder.configureStates()
                    .withStates()
                        .initial(Status.PENDING)
                        .states(EnumSet.allOf(Status.class));

            builder.configureTransitions()
                    .withExternal()
                        .source(Status.PENDING).target(Status.ARPPOVED)
                        .event(ActionEvent.APPROVE)
                        .and()
                    .withExternal()
                        .source(Status.PENDING).target(Status.DECLINED)
                        .event(ActionEvent.DECLINE)
                        .and()
                    .withExternal()
                        .source(Status.ARPPOVED).target(Status.PENDING)
                        .event(ActionEvent.RESET)
                        .and()
                    .withExternal()
                        .source(Status.DECLINED).target(Status.PENDING)
                        .event(ActionEvent.RESET);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return builder.build();

    }
}
