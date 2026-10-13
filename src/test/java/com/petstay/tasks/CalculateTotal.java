package com.petstay.tasks;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class CalculateTotal {

    public static Performable ofTheStay() {
        return Task.where("{0} calcula el total de la estadía", Click.on(BookingPage.CALCULATE_BUTTON));
    }
}
