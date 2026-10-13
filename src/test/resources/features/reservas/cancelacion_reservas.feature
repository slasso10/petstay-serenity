# language: es
@HU02 @CancelacionReservas
Característica: Mis reservas y cancelación
  Como dueño de una mascota
  quiero cancelar mis reservas confirmadas
  para recuperar el dinero según la política de reembolso

  @HU02-F3-CA2 @HU02-F3-CA3 @HU02-F3-CA4 @positivo @Issue-18
  Esquema del escenario: Reembolso según los días que faltan para la entrada
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Y tiene una reserva confirmada para "Rocky" de 2 noches que inicia <dias> día(s) desde hoy
    Cuando cancela su última reserva
    Y confirma la cancelación
    Entonces la reserva debería estar en estado "Cancelada"
    Y el reembolso debería ser "<reembolso>"

    Ejemplos:
      | dias | reembolso |
      | 3    | $110.000  |
      | 2    | $55.000   |
      | 1    | $55.000   |
      | 0    | $0        |

  @HU02-F3-CA2 @negativo @Issue-19
  Escenario: Cancelar una reserva pide confirmación y se puede abandonar
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Y tiene una reserva confirmada para "Rocky" de 2 noches que inicia 5 días desde hoy
    Cuando cancela su última reserva
    Entonces debería ver un diálogo de confirmación
    Cuando decide no cancelar
    Entonces la reserva debería estar en estado "Confirmada"

  @HU02-F3-CA4 @negativo @Issue-20
  Escenario: Una reserva cancelada no se puede cancelar de nuevo
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Y tiene una reserva confirmada para "Rocky" de 2 noches que inicia 5 días desde hoy
    Cuando cancela su última reserva
    Y confirma la cancelación
    Entonces la reserva debería estar en estado "Cancelada"
    Y la reserva ya no debería tener la opción de cancelar
