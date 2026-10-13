package com.petstay.tasks;

import com.petstay.ui.CommonUI;
import com.petstay.ui.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;

public class Login {

    public static Performable withCredentials(String user, String password) {
        return Task.where("{0} inicia sesión con el usuario " + user,
                Enter.theValue(user).into(LoginPage.USERNAME),
                Enter.theValue(password).into(LoginPage.PASSWORD),
                Click.on(LoginPage.LOGIN_BUTTON),
                WaitUntil.the(CommonUI.LOADING_OVERLAY, isNotVisible()).forNoMoreThan(20).seconds()
        );
    }
}
