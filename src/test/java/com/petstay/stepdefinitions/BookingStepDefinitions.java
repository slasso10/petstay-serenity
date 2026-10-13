package com.petstay.stepdefinitions;

import com.petstay.questions.TheLastBooking;
import com.petstay.tasks.AddService;
import com.petstay.tasks.BookStay;
import com.petstay.tasks.CalculateTotal;
import com.petstay.tasks.ConfirmBooking;
import com.petstay.tasks.GoTo;
import com.petstay.ui.BookingPage;
import com.petstay.ui.BookingsPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class BookingStepDefinitions {

    // ---------- Dado / Cuando ----------

    @Dado("tiene una reserva confirmada para {string} de {int} noche(s) que inicia {int} día(s) desde hoy")
    public void hasAConfirmedBooking(String pet, int nights, int startsInDays) {
        theActorInTheSpotlight().attemptsTo(
                BookStay.forPet(pet, nights, startsInDays),
                CalculateTotal.ofTheStay(),
                ConfirmBooking.ofTheStay());
    }

    @Cuando("arma una estadía para {string} de {int} noche(s) que inicia {int} día(s) desde hoy")
    public void buildsAStay(String pet, int nights, int startsInDays) {
        theActorInTheSpotlight().attemptsTo(BookStay.forPet(pet, nights, startsInDays));
    }

    @Cuando("agrega el servicio de baño y peluquería")
    public void addsBathAndGrooming() {
        theActorInTheSpotlight().attemptsTo(AddService.bathAndGrooming());
    }

    @Cuando("agrega el servicio de paseo diario")
    public void addsDailyWalk() {
        theActorInTheSpotlight().attemptsTo(AddService.dailyWalk());
    }

    @Cuando("calcula el total")
    public void calculatesTheTotal() {
        theActorInTheSpotlight().attemptsTo(CalculateTotal.ofTheStay());
    }

    @Cuando("confirma la reserva")
    public void confirmsTheBooking() {
        theActorInTheSpotlight().attemptsTo(ConfirmBooking.ofTheStay());
    }

    @Cuando("abre la sección Reservar")
    public void opensTheBookingSection() {
        theActorInTheSpotlight().attemptsTo(GoTo.booking());
    }

    // ---------- Entonces ----------

    @Entonces("el resumen debería mostrar {int} noche(s)")
    public void summaryShowsNights(int nights) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(BookingPage.SUMMARY_NIGHTS).text().isEqualTo(String.valueOf(nights)));
    }

    @Entonces("el resumen debería mostrar una tarifa por noche de {string}")
    public void summaryShowsTheRate(String rate) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.SUMMARY_RATE).text().isEqualTo(rate));
    }

    @Entonces("el resumen debería mostrar un total de {string}")
    public void summaryShowsTheTotal(String total) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.SUMMARY_TOTAL).text().isEqualTo(total));
    }

    @Entonces("el resumen debería mostrar el descuento por estadía larga")
    public void summaryShowsTheDiscount() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.SUMMARY_DISCOUNT_ROW).isDisplayed());
    }

    @Entonces("el paseo diario no debería estar disponible")
    public void dailyWalkIsNotAvailable() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.WALK_CHECKBOX).isDisabled());
    }

    @Entonces("debería ver la nota {string}")
    public void shouldSeeTheNote(String note) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.WALK_NOTE).text().isEqualTo(note));
    }

    @Entonces("debería ver el aviso {string}")
    public void shouldSeeTheNotice(String notice) {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.NO_PETS_NOTICE).text().contains(notice));
    }

    @Entonces("no debería ver el formulario de reserva")
    public void shouldNotSeeTheBookingForm() {
        theActorInTheSpotlight().attemptsTo(Ensure.that(BookingPage.BOOKING_FORM).isNotDisplayed());
    }

    @Entonces("el código de la reserva debería tener el formato PS-XXXX")
    public void bookingCodeHasTheExpectedFormat() {
        Actor actor = theActorInTheSpotlight();
        String code = actor.asksFor(TheLastBooking.code());
        actor.attemptsTo(Ensure.that(code.matches("PS-\\d{4}")).isTrue());
    }

    @Entonces("la reserva debería aparecer en Mis reservas con estado {string} y total {string}")
    public void bookingAppearsInMyBookings(String status, String total) {
        Actor actor = theActorInTheSpotlight();
        String code = actor.asksFor(TheLastBooking.code());
        actor.attemptsTo(
                GoTo.myBookings(),
                Ensure.that(BookingsPage.STATUS.of(code)).text().isEqualTo(status),
                Ensure.that(BookingsPage.TOTAL.of(code)).text().isEqualTo(total));
    }
}
