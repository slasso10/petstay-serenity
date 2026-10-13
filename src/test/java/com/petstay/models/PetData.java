package com.petstay.models;

import java.util.Map;

/** Datos de una mascota tal como se escriben en el formulario de registro. */
public record PetData(String name, String species, String weight, String age, boolean vaccinated) {

    /** Crea la mascota desde una fila de la tabla del Gherkin (nombre, especie, peso, edad, vacunas). */
    public static PetData fromRow(Map<String, String> row) {
        return new PetData(
                row.get("nombre"),
                row.get("especie"),
                row.get("peso"),
                row.get("edad"),
                isYes(row.get("vacunas")));
    }

    /** Mascota "estándar" con 3 años y vacunas al día; solo varían especie, nombre y peso. */
    public static PetData vaccinated(String species, String name, int weightKg) {
        return new PetData(name, capitalize(species), String.valueOf(weightKg), "3", true);
    }

    private static boolean isYes(String value) {
        String normalized = value == null ? "" : value.trim().toLowerCase();
        return normalized.equals("sí") || normalized.equals("si") || normalized.equals("true");
    }

    private static String capitalize(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1).toLowerCase();
    }
}
