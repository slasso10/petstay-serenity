package com.petstay.questions;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * "¿Qué día es hoy según PetStay?". PetStay calcula "hoy" en UTC, que de noche en Colombia ya es el día siguiente;
 * por eso las fechas de las pruebas se calculan a partir de lo que muestra la pantalla y no del reloj del computador.
 */
public class TodayInPetStay {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public static Question<LocalDate> date() {
        return Question.about("la fecha de hoy según PetStay")
                .answeredBy(actor -> LocalDate.parse(Text.of(BookingPage.TODAY).answeredBy(actor).trim(), FORMAT));
    }
}
