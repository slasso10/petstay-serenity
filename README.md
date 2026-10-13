# PetStay – Automatización Front-End con Serenity BDD

Pruebas end-to-end de [PetStay](https://petstay-aphn.onrender.com) (hotel para mascotas) con
**Serenity BDD + Cucumber (Gherkin en español) + patrón Screenplay**, Gradle y GitHub Actions.

## Requisitos
- JDK 17 o superior (`java -version`) y Google Chrome instalado.
- No hace falta instalar Gradle: se usa el wrapper (`./gradlew`).

## Ejecutar
```bash
./gradlew clean test                                   # todos los escenarios (Windows: gradlew.bat)
./gradlew clean test -Dcucumber.filter.tags="@smoketest"   # solo los de humo
./gradlew clean test -Dcucumber.filter.tags="@Issue-3"     # un caso (un issue)
./gradlew clean test -Dheadless.mode=true                  # sin abrir ventana de Chrome
```
Al terminar se genera el reporte: abre `target/site/serenity/index.html`.

## Estructura (src/test)
```
java/com/petstay/
  runners/CucumberTestSuite   -> arranca JUnit 5 + Cucumber; indica dónde están los .feature y el GLUE
  hooks/ScreenplayHooks       -> @Before arma el "elenco" (OnlineCast); tipo de parámetro {actor}
  stepdefinitions/            -> conectan cada frase del Gherkin con tareas y verificaciones (sin selectores)
  tasks/                      -> acciones con objetivo de negocio: Login, RegisterPet, BookStay, CancelBooking...
  questions/                  -> consultas a la pantalla: TodayInPetStay, TheLastBooking
  ui/                         -> Targets (localizadores) de cada página
  models/                     -> datos: PetData, Owner, RememberedFacts
resources/
  features/                   -> escenarios Gherkin (acceso, mascotas, reservas)
  serenity.conf               -> navegador, esperas, capturas
```
Recorrido de una prueba: `.feature` → *StepDefinitions* → **Actor** → **Task** → **Interaction** → **Target** (`ui/`).

## Decisiones de diseño (útiles para la sustentación)
- **Navegador limpio por escenario.** PetStay guarda sus datos en el navegador (mascotas, reservas). Por eso
  `serenity.conf` usa `restart.browser.for.each = scenario` y la tarea `OpenPetStay` borra `localStorage`.
  Así ningún escenario depende de otro ni del orden de ejecución.
- **Fechas relativas a "hoy".** Los escenarios dicen «inicia 1 día desde hoy». La pregunta `TodayInPetStay`
  lee el "hoy" que muestra la propia aplicación (PetStay lo calcula en UTC) y la tarea `BookStay` calcula las fechas.
  Nunca hay fechas fijas que caduquen.
- **Esperas sin `Thread.sleep`.** PetStay muestra una pantalla de carga (`#capa-cargando`) al iniciar sesión, registrar,
  confirmar y cancelar. Las tareas terminan con `WaitUntil` hasta que desaparece, y el resto lo cubre la espera implícita
  de `serenity.conf`.
- **Un solo localizador de mensajes.** `CommonUI.VISIBLE_MESSAGE` apunta al mensaje que esté visible (`:not([hidden])`)
  en cualquier página; así un mismo Step («debería ver el mensaje …») sirve para login, mascotas, reservas y cancelación.
- **Los Steps solo llaman tareas y verificaciones (`Ensure`)**; los localizadores viven únicamente en `ui/`.

## Trazabilidad requisito ↔ issue ↔ escenario
Cada escenario lleva: tag del requisito (`@HU02-F1-CA2` = HU-02, Funcionalidad 1, criterio de aceptación 2),
tag del issue (`@Issue-N`), tipo (`@positivo` / `@negativo`) y, en los básicos, `@smoketest`.

| Issue | Tag del issue | Requisito (tags) | Tipo | Escenario |
|---|---|---|---|---|
| #1 | `@Issue-1` | HU02-ACC-CA1 | positivo | Inicio de sesión con credenciales válidas |
| #2 | `@Issue-2` | HU02-ACC-CA2 | negativo | Rechazo de credenciales incorrectas (usuario o contraseña) |
| #3 | `@Issue-3` | HU02-F1-CA1, HU02-F1-CA5 | positivo | Registro de un perro con datos válidos |
| #4 | `@Issue-4` | HU02-F1-CA2 | negativo | Registro rechazado sin vacunas al día |
| #5 | `@Issue-5` | HU02-F1-CA3 | positivo | Tamaño automático según el peso (valores límite 10, 10.5, 24.9, 25) |
| #6 | `@Issue-6` | HU02-F1-CA1 | negativo | Rechazo de datos inválidos (peso 0 y 80.1, edad 26, nombre de 1 y 31 caracteres) |
| #7 | `@Issue-7` | HU02-F1-CA4 | negativo | Nombre duplicado sin distinguir mayúsculas |
| #8 | `@Issue-8` | HU02-F2-CA8, HU02-F2-CA10, HU02-F3-CA1 | positivo | Reserva con resumen y confirmación |
| #9 | `@Issue-9` | HU02-F2-CA6 | positivo | Total con baño y peluquería y paseo diario (perro) |
| #10 | `@Issue-10` | HU02-F2-CA6 | negativo | Paseo diario no disponible para gatos |
| #11 | `@Issue-11` | HU02-F2-CA5 | positivo | Tarifa por noche según tamaño (8, 12 y 30 kg) |
| #12 | `@Issue-12` | HU02-F2-CA4, HU02-F2-CA7 | positivo | Descuento 15% solo con más de 7 noches (7, 8 y 30 noches) |
| #13 | `@Issue-13` | HU02-F2-CA7 | positivo | El descuento no aplica a los servicios adicionales |
| #14 | `@Issue-14` | HU02-F2-CA3, HU02-F2-CA4 | negativo | Rechazo de fechas inválidas (pasado, salida igual o anterior a la entrada, 31 noches) |
| #15 | `@Issue-15` | HU02-F2-CA9 | negativo | Reservas con fechas cruzadas para la misma mascota |
| #16 | `@Issue-16` | HU02-F2-CA9 | positivo | La salida de una reserva puede ser la entrada de otra |
| #17 | `@Issue-17` | HU02-F2-CA1 | negativo | No se puede reservar sin mascotas registradas |
| #18 | `@Issue-18` | HU02-F3-CA2, HU02-F3-CA3, HU02-F3-CA4 | positivo | Reembolso según días antes de la entrada (3, 2, 1 y 0 días) |
| #19 | `@Issue-19` | HU02-F3-CA2 | negativo | Cancelar pide confirmación y se puede abandonar |
| #20 | `@Issue-20` | HU02-F3-CA4 | negativo | Una reserva cancelada no se puede cancelar de nuevo |

## Cómo dejar todo en GitHub
1. Crea un repositorio vacío en GitHub y sube el proyecto:
   ```bash
   git init && git add . && git commit -m "Automatización PetStay con Serenity BDD"
   git branch -M main && git remote add origin <URL-del-repo> && git push -u origin main
   ```
   Verifica que `.github/workflows/ci.yml` quedó en el repo: el push a `main` ya dispara el pipeline con `@smoketest`.
2. Crea los 20 issues **antes de hacer más issues o PRs** (para que los números coincidan con `@Issue-N`):
   `./scripts/crear-issues.sh` (necesita GitHub CLI: `gh auth login`). Si prefieres hacerlo a mano, usa el título,
   requisito y tag de la tabla de arriba.
3. Crea el **Project** (pestaña *Projects* → *New project* → Board) y agrega los 20 issues; ese es tu *TestCases*.
4. Ejecución desde el TestCase: *Actions → CI / CD Pipeline → Run workflow* con `testTag = @Issue-3`.
   El pipeline ejecuta solo ese escenario y le agrega la etiqueta `status:passed` o `status:failed` al issue.
5. Descarga el artefacto `serenity-report` de la corrida para ver el reporte con capturas.

## Preparación para la sustentación
Practica poder explicar y modificar en vivo:
1. **Screenplay:** Actor (Ana) → Habilidad (navegar, `OnlineCast`) → Tarea (`Login`, `RegisterPet`) → Interacción
   (`Enter`, `Click`) → Target (`ui/`). Pregunta: `TodayInPetStay`. ¿Qué cambia si cambia un localizador? Solo `ui/`.
2. **Gherkin:** `Dado/Cuando/Entonces`, `Esquema del escenario` + `Ejemplos`, tablas de datos, `Antecedentes`.
   Describe *qué* hace el usuario, no *cómo*.
3. **Trazabilidad:** tag `@Issue-N` ↔ issue ↔ Project; ejecutar un caso con `-Dcucumber.filter.tags="@Issue-N"`.
4. **Cambio en vivo típico:** añadir un ejemplo a un esquema (p. ej. 80 kg válido), cambiar un mensaje esperado,
   o escribir un localizador a mano (orden de preferencia: `id` → atributo estable/`data-testid` → CSS → XPath relativo;
   nunca XPath absoluto). Practica con *clic derecho → Inspeccionar → Ctrl+F* en la pestaña Elements.
5. **Pipeline:** qué hace cada paso de `ci.yml` y por qué `-Dheadless.mode=true` en CI.
