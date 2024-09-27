package com.studies.algafood.jpa.state;

import com.studies.algafood.AlgafoodApiApplication;
import com.studies.algafood.domain.model.State;
import com.studies.algafood.domain.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class StatesMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        StateRepository allStates =  applicationContext.getBean(StateRepository.class);

        List<State> stateList =  allStates.list();
        stateList.forEach(System.out::println);

        State state = allStates.find(1L);
        System.out.println(state);

        State newState = allStates.save(new State("Novo state"));

        newState.setName("NOVO NOME");

        allStates.save(newState);

        allStates.list().forEach(System.out::println);
    }
}
