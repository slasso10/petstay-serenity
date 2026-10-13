# language: es
@HU02 @RegistroMascotas
Característica: Registro de mascotas
  Como dueño de una mascota
  quiero registrar a mis mascotas
  para poder reservar estadías para ellas

  @HU02-F1-CA1 @HU02-F1-CA5 @positivo @smoketest @Issue-3
  Escenario: Registro de un perro con datos válidos
    Dado que Ana inició sesión en PetStay
    Cuando registra una mascota con los siguientes datos:
      | nombre | especie | peso | edad | vacunas |
      | Rocky  | Perro   | 12   | 3    | sí      |
    Entonces debería ver el mensaje "Rocky fue registrado con tamaño Mediano"
    Y la lista de mascotas debería mostrar a "Rocky" como "Perro" de "12 kg" y "3 años" con tamaño "Mediano"

  @HU02-F1-CA2 @negativo @Issue-4
  Escenario: Registro rechazado cuando la mascota no tiene las vacunas al día
    Dado que Ana inició sesión en PetStay
    Cuando registra una mascota con los siguientes datos:
      | nombre | especie | peso | edad | vacunas |
      | Luna   | Gato    | 4    | 2    | no      |
    Entonces debería ver el mensaje "No podemos hospedar mascotas sin vacunas al día"
    Y la lista de mascotas debería estar vacía

  @HU02-F1-CA3 @positivo @Issue-5
  Esquema del escenario: Asignación automática del tamaño según el peso
    Dado que Ana inició sesión en PetStay
    Cuando registra una mascota con los siguientes datos:
      | nombre | especie | peso   | edad | vacunas |
      | Toby   | Perro   | <peso> | 4    | sí      |
    Entonces la lista de mascotas debería mostrar a "Toby" con tamaño "<tamaño>"

    Ejemplos:
      | peso | tamaño  |
      | 10   | Pequeño |
      | 10.5 | Mediano |
      | 24.9 | Mediano |
      | 25   | Grande  |

  @HU02-F1-CA1 @negativo @Issue-6
  Esquema del escenario: Rechazo de datos inválidos en el registro
    Dado que Ana inició sesión en PetStay
    Cuando registra una mascota con los siguientes datos:
      | nombre   | especie | peso   | edad   | vacunas |
      | <nombre> | Perro   | <peso> | <edad> | sí      |
    Entonces debería ver el error "<error>" en el campo "<campo>"

    Ejemplos:
      | nombre                          | peso | edad | campo  | error                                         |
      | Rex                             | 0    | 3    | peso   | El peso debe ser mayor que 0 y máximo 80 kg   |
      | Rex                             | 80.1 | 3    | peso   | El peso debe ser mayor que 0 y máximo 80 kg   |
      | Rex                             | 12   | 26   | edad   | La edad debe estar entre 0 y 25 años          |
      | R                               | 12   | 3    | nombre | El nombre debe tener entre 2 y 30 caracteres  |
      | abcdefghijklmnopqrstuvwxyzabcde | 12   | 3    | nombre | El nombre debe tener entre 2 y 30 caracteres  |

  @HU02-F1-CA4 @negativo @Issue-7
  Escenario: No se permiten dos mascotas con el mismo nombre sin distinguir mayúsculas
    Dado que Ana inició sesión en PetStay
    Y tiene registrado un perro llamado "Luna" de 8 kg
    Cuando registra una mascota con los siguientes datos:
      | nombre | especie | peso | edad | vacunas |
      | LUNA   | Gato    | 4    | 2    | sí      |
    Entonces debería ver el error "Ya existe una mascota registrada con ese nombre" en el campo "nombre"
