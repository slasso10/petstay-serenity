package com.petstay.ui;

import net.serenitybdd.screenplay.targets.Target;

/** Página "Mis mascotas": formulario de registro y lista de mascotas. */
public class PetsPage {
    public static final Target NAME = Target.the("campo nombre de la mascota").locatedBy("#nombreMascota");

    /** Opción de especie. Uso: SPECIES_OPTION.of("Perro"). Se hace clic en la etiqueta del radio. */
    public static final Target SPECIES_OPTION = Target.the("opción de especie")
            .locatedBy("//label[contains(@class,'opcion-radio')][.//span[normalize-space()='{0}']]");

    public static final Target WEIGHT = Target.the("campo peso").locatedBy("[name='peso']");
    public static final Target AGE = Target.the("campo edad").locatedBy("[data-testid='edad']");
    public static final Target VACCINES = Target.the("casilla vacunas al día")
            .locatedBy("//label[contains(@class,'opcion-check')][.//span[contains(normalize-space(),'vacunas')]]");
    public static final Target REGISTER_BUTTON = Target.the("botón registrar mascota")
            .locatedBy("#form-mascota button[type='submit']");

    /** Celda de la fila de una mascota. Uso: PET_CELL.of("Rocky", "4") -> 1 especie, 2 peso, 3 edad, 4 tamaño. */
    public static final Target PET_CELL = Target.the("celda de la mascota")
            .locatedBy("//td[@class='nombre'][normalize-space()='{0}']/following-sibling::td[{1}]");

    /** Enlace "Reservar" de la fila de una mascota. Uso: BOOK_LINK.of("Rocky"). */
    public static final Target BOOK_LINK = Target.the("enlace Reservar de la mascota")
            .locatedBy("//td[@class='nombre'][normalize-space()='{0}']/following-sibling::td//a[normalize-space()='Reservar']");

    public static final Target EMPTY_LIST_NOTICE = Target.the("aviso de lista de mascotas vacía")
            .locatedBy(".lista-vacia");
}
