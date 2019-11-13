package junyeong.yu.statemachine.config;

import junyeong.yu.statemachine.enums.ActionEvent;
import junyeong.yu.statemachine.enums.Status;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<Status, ActionEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<Status, ActionEvent> config) throws Exception {
        config.withConfiguration().autoStartup(true).listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<Status, ActionEvent> statuses) throws Exception {
        statuses.withStates().initial(Status.PENDING).states(EnumSet.allOf(Status.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<Status, ActionEvent> transitions) throws Exception {
        transitions
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
    }

    @Bean
    public StateMachineListener<Status, ActionEvent> listener() {
        return new StateMachineListenerAdapter<Status, ActionEvent>() {
            @Override
            public void stateChanged(State<Status, ActionEvent> from, State<Status, ActionEvent> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
