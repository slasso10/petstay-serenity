package com.petstay.stepdefinitions;

import com.petstay.ui.CommonUI;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/** Verificaciones que se usan en varias páginas. Los Steps solo llaman tareas y verificaciones. */
public class CommonStepDefinitions {

    @Entonces("debería ver la página {string}")
    public void shouldSeeThePage(String title) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(CommonUI.PAGE_TITLE).text().isEqualTo(title));
    }

    @Entonces("debería ver el mensaje {string}")
    public void shouldSeeTheMessage(String message) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(CommonUI.VISIBLE_MESSAGE).text().isEqualTo(message));
    }

    @Entonces("debería ver un mensaje que contiene {string}")
    public void shouldSeeAMessageContaining(String text) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(CommonUI.VISIBLE_MESSAGE).text().contains(text));
    }

    @Entonces("debería ver el error {string} en el campo {string}")
    public void shouldSeeTheFieldError(String error, String field) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(CommonUI.FIELD_ERROR.of(field)).text().isEqualTo(error));
    }
}
