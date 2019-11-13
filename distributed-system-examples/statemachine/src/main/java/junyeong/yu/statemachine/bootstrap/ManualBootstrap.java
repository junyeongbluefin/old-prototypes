package junyeong.yu.statemachine.bootstrap;

import junyeong.yu.statemachine.enums.ActionEvent;
import junyeong.yu.statemachine.StateMachineBuilder;
import junyeong.yu.statemachine.enums.Status;
import org.springframework.statemachine.StateMachine;

public class ManualBootstrap {

    public void init() {
        StateMachine<Status, ActionEvent> stateMachine = new StateMachineBuilder().buildMachine();
        stateMachine.start();
        stateMachine.sendEvent(ActionEvent.APPROVE);
        System.out.println(stateMachine.getState());
        stateMachine.sendEvent(ActionEvent.DECLINE);
        System.out.println(stateMachine.getState());
        stateMachine.sendEvent(ActionEvent.RESET);
        System.out.println(stateMachine.getState());
    }
}
