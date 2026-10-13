package com.petstay.questions;

import com.petstay.models.RememberedFacts;
import net.serenitybdd.screenplay.Question;

/** El código (PS-XXXX) de la última reserva que confirmó el actor. */
public class TheLastBooking {
    public static Question<String> code() {
        return Question.about("el código de la última reserva")
                .answeredBy(actor -> actor.recall(RememberedFacts.LAST_BOOKING_CODE));
    }
}
