package com.petstay.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ScreenplayHooks {

    /** Antes de cada escenario se arma la "escena": cada actor que aparezca recibe su propio navegador. */
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    /** Permite escribir "Dado que Ana ..." en el Gherkin y recibir un Actor en el Step. */
    @ParameterType("[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }
}
