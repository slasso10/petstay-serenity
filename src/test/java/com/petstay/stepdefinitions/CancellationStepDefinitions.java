package com.petstay.stepdefinitions;

import com.petstay.questions.TheLastBooking;
import com.petstay.tasks.CancelBooking;
import com.petstay.ui.BookingsPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CancellationStepDefinitions {

    @Cuando("cancela su última reserva")
    public void cancelsTheLastBooking() {
        theActorInTheSpotlight().attemptsTo(CancelBooking.lastOne());
    }

    @Cuando("confirma la cancelación")
    public void confirmsTheCancellation() {
        theActorInTheSpotlight().attemptsTo(CancelBooking.confirm());
    }

    @Cuando("decide no cancelar")
    public void decidesNotToCancel() {
        theActorInTheSpotlight().attemptsTo(CancelBooking.keepTheBooking());
    }

    @Entonces("debería ver un diálogo de confirmación")
    public void shouldSeeTheConfirmationDialog() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(BookingsPage.CONFIRMATION_DIALOG).isDisplayed(),
                Ensure.that(BookingsPage.CONFIRMATION_TEXT).text().contains("¿Deseas cancelar la reserva"));
    }

    @Entonces("la reserva debería estar en estado {string}")
    public void bookingShouldHaveStatus(String status) {
        Actor actor = theActorInTheSpotlight();
        String code = actor.asksFor(TheLastBooking.code());
        actor.attemptsTo(Ensure.that(BookingsPage.STATUS.of(code)).text().isEqualTo(status));
    }

    @Entonces("el reembolso debería ser {string}")
    public void refundShouldBe(String refund) {
        Actor actor = theActorInTheSpotlight();
        String code = actor.asksFor(TheLastBooking.code());
        actor.attemptsTo(Ensure.that(BookingsPage.REFUND.of(code)).text().isEqualTo(refund));
    }

    @Entonces("la reserva ya no debería tener la opción de cancelar")
    public void bookingCannotBeCancelledAgain() {
        Actor actor = theActorInTheSpotlight();
        String code = actor.asksFor(TheLastBooking.code());
        actor.attemptsTo(Ensure.that(BookingsPage.ACTIONS.of(code)).text().isEmpty());
    }
}
