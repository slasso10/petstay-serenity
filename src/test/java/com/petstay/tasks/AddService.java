package com.petstay.tasks;

import com.petstay.ui.BookingPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class AddService {

    public static Performable bathAndGrooming() {
        return Task.where("{0} agrega el servicio de baño y peluquería", Click.on(BookingPage.BATH_SERVICE));
    }

    public static Performable dailyWalk() {
        return Task.where("{0} agrega el servicio de paseo diario", Click.on(BookingPage.WALK_SERVICE));
    }
}
