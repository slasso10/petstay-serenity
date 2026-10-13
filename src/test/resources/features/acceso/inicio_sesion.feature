# language: es
@HU02 @Acceso
Característica: Acceso a PetStay
  Como dueño de una mascota
  quiero iniciar sesión con mi usuario y contraseña
  para usar el hotel de mascotas

  @HU02-ACC-CA1 @positivo @smoketest @Issue-1
  Escenario: Inicio de sesión con credenciales válidas
    Dado que Ana está en la página de inicio de sesión
    Cuando inicia sesión con el usuario "dueno" y la contraseña "huellitas123"
    Entonces debería ver la página "Mis mascotas"

  @HU02-ACC-CA2 @negativo @Issue-2
  Esquema del escenario: Rechazo de credenciales incorrectas
    Dado que Ana está en la página de inicio de sesión
    Cuando inicia sesión con el usuario "<usuario>" y la contraseña "<contraseña>"
    Entonces debería ver el mensaje "Usuario o contraseña incorrectos"

    Ejemplos:
      | usuario | contraseña |
      | dueno   | incorrecta |
      | intruso | huellitas123 |
