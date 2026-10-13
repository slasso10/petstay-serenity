package com.petstay.tasks;

import com.petstay.questions.TheLastBooking;
import com.petstay.ui.BookingsPage;
import com.petstay.ui.CommonUI;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class CancelBooking {

    /** Va a Mis reservas y pulsa "Cancelar" en la última reserva (abre el diálogo de confirmación). */
    public static Performable lastOne() {
        return Task.where("{0} pide cancelar su última reserva", actor -> {
            String code = actor.asksFor(TheLastBooking.code());
            actor.attemptsTo(
                    Click.on(CommonUI.MENU_MY_BOOKINGS),
                    Click.on(BookingsPage.CANCEL_BUTTON.of(code)));
        });
    }

    public static Performable confirm() {
        return Task.where("{0} confirma la cancelación",
                Click.on(BookingsPage.CONFIRM_CANCELLATION),
                WaitUntil.the(CommonUI.LOADING_OVERLAY, isNotVisible()).forNoMoreThan(20).seconds());
    }

    public static Performable keepTheBooking() {
        return Task.where("{0} decide no cancelar y vuelve", Click.on(BookingsPage.KEEP_BOOKING));
    }
}
