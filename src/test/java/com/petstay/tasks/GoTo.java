package com.petstay.tasks;

import com.petstay.ui.CommonUI;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

/** Navegación con el menú principal de PetStay. */
public class GoTo {

    public static Performable myPets() {
        return Task.where("{0} entra a Mis mascotas", Click.on(CommonUI.MENU_MY_PETS));
    }

    public static Performable booking() {
        return Task.where("{0} entra a Reservar", Click.on(CommonUI.MENU_BOOKING));
    }

    public static Performable myBookings() {
        return Task.where("{0} entra a Mis reservas", Click.on(CommonUI.MENU_MY_BOOKINGS));
    }
}
