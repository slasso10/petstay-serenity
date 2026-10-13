package com.petstay.tasks;

import com.petstay.models.PetData;
import com.petstay.ui.CommonUI;
import com.petstay.ui.PetsPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class RegisterPet {

    public static Performable with(PetData pet) {
        return Task.where("{0} registra a la mascota " + pet.name(), actor -> {
            actor.attemptsTo(
                    Enter.theValue(pet.name()).into(PetsPage.NAME),
                    Click.on(PetsPage.SPECIES_OPTION.of(pet.species())),
                    Enter.theValue(pet.weight()).into(PetsPage.WEIGHT),
                    Enter.theValue(pet.age()).into(PetsPage.AGE));
            if (pet.vaccinated()) {
                actor.attemptsTo(Click.on(PetsPage.VACCINES));
            }
            actor.attemptsTo(
                    Click.on(PetsPage.REGISTER_BUTTON),
                    // Si el registro es válido PetStay muestra una pantalla de carga; esperamos a que termine.
                    WaitUntil.the(CommonUI.LOADING_OVERLAY, isNotVisible()).forNoMoreThan(20).seconds());
        });
    }
}
