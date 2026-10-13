package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

/** Página "Mis reservas": lista, cancelación y diálogo de confirmación. Los Targets con {0} reciben el código PS-XXXX. */
public class BookingsPage {
    private static final String ROW = "//tr[td[@class='codigo'][normalize-space()='{0}']]";

    public static final Target STATUS = Target.the("estado de la reserva").locatedBy(ROW + "//span[contains(@class,'estado')]");
    public static final Target TOTAL = Target.the("total de la reserva").locatedBy(ROW + "/td[@class='total']");
    public static final Target REFUND = Target.the("reembolso de la reserva").locatedBy(ROW + "/td[@class='reembolso']");
    public static final Target ACTIONS = Target.the("acciones de la reserva").locatedBy(ROW + "/td[last()]");
    public static final Target CANCEL_BUTTON = Target.the("botón cancelar de la reserva")
            .locatedBy("button[data-codigo='{0}']");

    public static final Target CONFIRMATION_DIALOG = Target.the("diálogo de cancelación")
            .locatedBy(".fondo-modal .modal");
    public static final Target CONFIRMATION_TEXT = Target.the("texto del diálogo de cancelación")
            .locatedBy(".texto-modal");
    public static final Target CONFIRM_CANCELLATION = Target.the("botón Sí, cancelar")
            .locatedBy("#confirmar-cancelacion");
    public static final Target KEEP_BOOKING = Target.the("botón No, volver")
            .locatedBy("[data-accion='volver']");
}
