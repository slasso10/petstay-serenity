package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

/** Elementos que se repiten en todas las páginas de PetStay. */
public class CommonUI {
    public static final Target MENU_MY_PETS = Target.the("menú Mis mascotas")
            .locatedBy("nav.menu a[href='mascotas.html']");
    public static final Target MENU_BOOKING = Target.the("menú Reservar")
            .locatedBy("nav.menu a[href='reservar.html']");
    public static final Target MENU_MY_BOOKINGS = Target.the("menú Mis reservas")
            .locatedBy("#enlace-reservas");

    public static final Target PAGE_TITLE = Target.the("título de la página")
            .locatedBy("main.contenido h1");

    /** Pantalla de carga ("Reservando la habitación...") que PetStay muestra en las operaciones lentas. */
    public static final Target LOADING_OVERLAY = Target.the("pantalla de carga")
            .locatedBy("#capa-cargando");

    /**
     * Mensaje que esté visible en la página actual (cada página tiene el suyo y se oculta con el atributo hidden):
     * error de login, mensaje de mascotas, de reserva o de mis reservas.
     */
    public static final Target VISIBLE_MESSAGE = Target.the("mensaje visible en pantalla")
            .locatedBy(".mensaje:not([hidden]), #error-login:not([hidden])");

    /** Error debajo de un campo de formulario. Uso: FIELD_ERROR.of("peso"). */
    public static final Target FIELD_ERROR = Target.the("error del campo")
            .locatedBy("[data-campo='{0}']");
}
