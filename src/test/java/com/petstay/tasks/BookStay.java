package com.petstay.tasks;

import com.petstay.questions.TodayInPetStay;
import com.petstay.ui.BookingPage;
import com.petstay.ui.CommonUI;
import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import java.time.LocalDate;

public class BookStay {

    /**
     * Abre el formulario de reserva de la mascota y escribe las fechas.
     * Las fechas son relativas a "hoy" (el que muestra PetStay): la entrada es hoy + startsInDays
     * y la salida es la entrada + nights.
     */
    public static Performable forPet(String petName, int nights, int startsInDays) {
        return Task.where("{0} arma una estadía de " + nights + " noches para " + petName
                + " que inicia en " + startsInDays + " días", actor -> {
            actor.attemptsTo(
                    Click.on(CommonUI.MENU_MY_PETS),
                    Click.on(PetsPage.BOOK_LINK.of(petName)));

            LocalDate today = actor.asksFor(TodayInPetStay.date());
            LocalDate entry = today.plusDays(startsInDays);
            LocalDate exit = entry.plusDays(nights);

            actor.attemptsTo(
                    Enter.theValue(TodayInPetStay.FORMAT.format(entry)).into(BookingPage.ENTRY_DATE),
                    Enter.theValue(TodayInPetStay.FORMAT.format(exit)).into(BookingPage.EXIT_DATE));
        });
    }
}
