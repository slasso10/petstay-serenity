package com.petstay.stepdefinitions;

import com.petstay.models.Owner;
import com.petstay.tasks.Login;
import com.petstay.tasks.OpenPetStay;
import com.petstay.ui.CommonUI;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class AccessStepDefinitions {

    @Dado("que {actor} está en la página de inicio de sesión")
    public void isOnTheLoginPage(Actor actor) {
        actor.attemptsTo(OpenPetStay.onTheLoginPage());
    }

    @Dado("que {actor} inició sesión en PetStay")
    public void hasLoggedIn(Actor actor) {
        actor.attemptsTo(
                OpenPetStay.onTheLoginPage(),
                Login.withCredentials(Owner.USERNAME, Owner.PASSWORD),
                Ensure.that(CommonUI.PAGE_TITLE).text().isEqualTo("Mis mascotas"));
    }

    @Cuando("inicia sesión con el usuario {string} y la contraseña {string}")
    public void logsIn(String user, String password) {
        theActorInTheSpotlight().attemptsTo(Login.withCredentials(user, password));
    }
}
