package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

/** Página "Reservar estadía": formulario, resumen y confirmación. */
public class BookingPage {
    public static final Target TODAY = Target.the("fecha de hoy según PetStay")
            .locatedBy("[data-testid='fecha-hoy']");
    public static final Target ENTRY_DATE = Target.the("campo fecha de entrada").locatedBy("#fecha-entrada");
    public static final Target EXIT_DATE = Target.the("campo fecha de salida").locatedBy("[data-testid='fecha-salida']");

    public static final Target BATH_SERVICE = Target.the("servicio baño y peluquería")
            .locatedBy("//label[contains(@class,'opcion-check')][.//span[contains(normalize-space(),'Baño y peluquería')]]");
    public static final Target WALK_SERVICE = Target.the("servicio paseo diario")
            .locatedBy("//label[contains(@class,'opcion-check')][.//span[contains(normalize-space(),'Paseo diario')]]");
    public static final Target WALK_CHECKBOX = Target.the("casilla paseo diario").locatedBy("[name='paseo']");
    public static final Target WALK_NOTE = Target.the("nota del paseo solo para perros").locatedBy(".nota-paseo");

    public static final Target CALCULATE_BUTTON = Target.the("botón calcular total").locatedBy("#btn-calcular");
    public static final Target CONFIRM_BUTTON = Target.the("botón confirmar reserva")
            .locatedBy(".panel-resumen .btn-primario");

    public static final Target SUMMARY_NIGHTS = Target.the("noches del resumen").locatedBy("[data-testid='res-noches']");
    public static final Target SUMMARY_RATE = Target.the("tarifa por noche del resumen").locatedBy(".res-tarifa");
    public static final Target SUMMARY_DISCOUNT_ROW = Target.the("fila de descuento del resumen")
            .locatedBy(".fila-descuento");
    public static final Target SUMMARY_TOTAL = Target.the("total del resumen").locatedBy("[data-testid='res-total']");

    public static final Target BOOKING_CODE = Target.the("código de la reserva")
            .locatedBy("[data-testid='codigo-reserva']");

    public static final Target NO_PETS_NOTICE = Target.the("aviso de que no hay mascotas").locatedBy(".sin-mascotas");
    public static final Target BOOKING_FORM = Target.the("formulario de reserva").locatedBy("#form-reserva");
}
