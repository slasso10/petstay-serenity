package com.petstay.tasks;

import com.petstay.models.RememberedFacts;
import com.petstay.ui.BookingPage;
import com.petstay.ui.CommonUI;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConfirmBooking {

    /** Confirma la reserva y guarda en la memoria del actor el código PS-XXXX que genera PetStay. */
    public static Performable ofTheStay() {
        return Task.where("{0} confirma la reserva", actor -> {
            actor.attemptsTo(
                    Click.on(BookingPage.CONFIRM_BUTTON),
                    WaitUntil.the(CommonUI.LOADING_OVERLAY, isNotVisible()).forNoMoreThan(20).seconds(),
                    WaitUntil.the(BookingPage.BOOKING_CODE, isVisible()).forNoMoreThan(10).seconds());
            String code = Text.of(BookingPage.BOOKING_CODE).answeredBy(actor).trim();
            actor.remember(RememberedFacts.LAST_BOOKING_CODE, code);
        });
    }
}
