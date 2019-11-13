package junyeong.yu.statemachine.bootstrap;

import junyeong.yu.statemachine.enums.ActionEvent;
import junyeong.yu.statemachine.enums.Status;
import lombok.AllArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConfiguredBootstrap {

    private final StateMachine<Status, ActionEvent> stateMachine;

    public void init() {
        stateMachine.sendEvent(ActionEvent.APPROVE);
        System.out.println(stateMachine.getState());
        stateMachine.sendEvent(ActionEvent.DECLINE);
        System.out.println(stateMachine.getState());
        stateMachine.sendEvent(ActionEvent.RESET);
        System.out.println(stateMachine.getState());
    }
}
