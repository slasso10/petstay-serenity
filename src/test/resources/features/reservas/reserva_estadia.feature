# language: es
@HU02 @ReservaEstadia
Característica: Reserva de estadía
  Como dueño de una mascota
  quiero reservar estadías con servicios adicionales
  para que mi mascota esté bien cuidada cuando no pueda atenderla

  Las fechas de los escenarios son relativas a hoy: "que inicia 1 día desde hoy" significa que la entrada es mañana.

  @HU02-F2-CA8 @HU02-F2-CA10 @HU02-F3-CA1 @positivo @smoketest @Issue-8
  Escenario: Reserva de una estadía con resumen y confirmación
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Cuando arma una estadía para "Rocky" de 2 noches que inicia 1 día desde hoy
    Y calcula el total
    Entonces el resumen debería mostrar 2 noches
    Y el resumen debería mostrar un total de "$110.000"
    Cuando confirma la reserva
    Entonces debería ver un mensaje que contiene "Reserva confirmada con el código PS-"
    Y el código de la reserva debería tener el formato PS-XXXX
    Y la reserva debería aparecer en Mis reservas con estado "Confirmada" y total "$110.000"

  @HU02-F2-CA6 @positivo @Issue-9
  Escenario: Total con baño y peluquería y paseo diario para un perro
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Cuando arma una estadía para "Rocky" de 3 noches que inicia 1 día desde hoy
    Y agrega el servicio de baño y peluquería
    Y agrega el servicio de paseo diario
    Y calcula el total
    Entonces el resumen debería mostrar un total de "$220.000"

  @HU02-F2-CA6 @negativo @Issue-10
  Escenario: El paseo diario no está disponible para los gatos
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un gato llamado "Misu" de 4 kg
    Cuando arma una estadía para "Misu" de 2 noches que inicia 1 día desde hoy
    Entonces el paseo diario no debería estar disponible
    Y debería ver la nota "El paseo solo está disponible para perros."

  @HU02-F2-CA5 @positivo @Issue-11
  Esquema del escenario: Tarifa por noche según el tamaño de la mascota
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Toby" de <peso> kg
    Cuando arma una estadía para "Toby" de 1 noche que inicia 1 día desde hoy
    Y calcula el total
    Entonces el resumen debería mostrar una tarifa por noche de "<tarifa>"

    Ejemplos:
      | peso | tarifa   |
      | 8    | $40.000  |
      | 12   | $55.000  |
      | 30   | $70.000  |

  @HU02-F2-CA4 @HU02-F2-CA7 @positivo @Issue-12
  Esquema del escenario: El descuento del 15% aplica solo con más de 7 noches
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Cuando arma una estadía para "Rocky" de <noches> noches que inicia 1 día desde hoy
    Y calcula el total
    Entonces el resumen debería mostrar un total de "<total>"

    Ejemplos:
      | noches | total      |
      | 7      | $385.000   |
      | 8      | $374.000   |
      | 30     | $1.402.500 |

  @HU02-F2-CA7 @positivo @Issue-13
  Escenario: El descuento no aplica a los servicios adicionales
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Cuando arma una estadía para "Rocky" de 10 noches que inicia 1 día desde hoy
    Y agrega el servicio de baño y peluquería
    Y calcula el total
    Entonces el resumen debería mostrar el descuento por estadía larga
    Y el resumen debería mostrar un total de "$492.500"

  @HU02-F2-CA3 @HU02-F2-CA4 @negativo @Issue-14
  Esquema del escenario: Rechazo de fechas inválidas
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Cuando arma una estadía para "Rocky" de <noches> noches que inicia <inicio> días desde hoy
    Y calcula el total
    Entonces debería ver el error "<error>" en el campo "<campo>"

    Ejemplos:
      | inicio | noches | campo   | error                                                |
      | -1     | 2      | entrada | La fecha de entrada no puede ser anterior a hoy      |
      | 1      | 0      | salida  | La fecha de salida debe ser posterior a la de entrada |
      | 1      | -1     | salida  | La fecha de salida debe ser posterior a la de entrada |
      | 1      | 31     | salida  | La estadía máxima es de 30 noches                    |

  @HU02-F2-CA9 @negativo @Issue-15
  Escenario: Una mascota no puede tener dos reservas activas con fechas cruzadas
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Y tiene una reserva confirmada para "Rocky" de 3 noches que inicia 2 días desde hoy
    Cuando arma una estadía para "Rocky" de 2 noches que inicia 3 días desde hoy
    Y calcula el total
    Entonces debería ver el mensaje "Esta mascota ya tiene una reserva activa en esas fechas"

  @HU02-F2-CA9 @positivo @Issue-16
  Escenario: El día de salida de una reserva puede ser el día de entrada de otra
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Rocky" de 12 kg
    Y tiene una reserva confirmada para "Rocky" de 3 noches que inicia 2 días desde hoy
    Cuando arma una estadía para "Rocky" de 2 noches que inicia 5 días desde hoy
    Y calcula el total
    Y confirma la reserva
    Entonces debería ver un mensaje que contiene "Reserva confirmada con el código PS-"

  @HU02-F2-CA1 @negativo @Issue-17
  Escenario: No se puede reservar sin tener mascotas registradas
    Dado que Ana inició sesión en PetStay
    Cuando abre la sección Reservar
    Entonces debería ver el aviso "Primero debes registrar una mascota."
    Y no debería ver el formulario de reserva
