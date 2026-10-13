package com.petstay.stepdefinitions;

import com.petstay.models.PetData;
import com.petstay.tasks.RegisterPet;
import com.petstay.ui.PetsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class PetStepDefinitions {

    @Dado("tiene registrado un {word} llamado {string} de {int} kg")
    public void hasRegisteredAPet(String species, String name, int weightKg) {
        theActorInTheSpotlight().attemptsTo(RegisterPet.with(PetData.vaccinated(species, name, weightKg)));
    }

    @Cuando("registra una mascota con los siguientes datos:")
    public void registersAPet(DataTable data) {
        PetData pet = PetData.fromRow(data.asMaps().get(0));
        theActorInTheSpotlight().attemptsTo(RegisterPet.with(pet));
    }

    @Entonces("la lista de mascotas debería mostrar a {string} como {string} de {string} y {string} con tamaño {string}")
    public void petListShowsThePet(String name, String species, String weight, String age, String size) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(PetsPage.PET_CELL.of(name, "1")).text().isEqualTo(species),
                Ensure.that(PetsPage.PET_CELL.of(name, "2")).text().isEqualTo(weight),
                Ensure.that(PetsPage.PET_CELL.of(name, "3")).text().isEqualTo(age),
                Ensure.that(PetsPage.PET_CELL.of(name, "4")).text().isEqualTo(size));
    }

    @Entonces("la lista de mascotas debería mostrar a {string} con tamaño {string}")
    public void petListShowsThePetSize(String name, String size) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(PetsPage.PET_CELL.of(name, "4")).text().isEqualTo(size));
    }

    @Entonces("la lista de mascotas debería estar vacía")
    public void petListIsEmpty() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(PetsPage.EMPTY_LIST_NOTICE).isDisplayed());
    }
}
