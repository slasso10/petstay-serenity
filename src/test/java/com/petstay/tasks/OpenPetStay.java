package com.petstay.tasks;

import com.petstay.ui.PetStayApp;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Evaluate;
import net.serenitybdd.screenplay.actions.Open;

public class OpenPetStay {

    /** Abre PetStay y borra los datos guardados en el navegador, para que cada escenario parta de cero. */
    public static Performable onTheLoginPage() {
        return Task.where("{0} abre PetStay con el navegador limpio",
                Open.url(PetStayApp.URL),
                Evaluate.javascript("window.localStorage.clear(); window.sessionStorage.clear();"),
                Open.url(PetStayApp.URL));
    }
}
