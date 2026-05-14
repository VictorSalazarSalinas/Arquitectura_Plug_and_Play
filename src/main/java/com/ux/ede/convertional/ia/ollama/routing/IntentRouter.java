package com.ux.ede.convertional.ia.ollama.routing;

public class IntentRouter {

    // Retorna un rol dependiendo de las palabras clave encontradas
    public String determinarRol(String instruccion) {
        if (instruccion == null || instruccion.isBlank()) return "Asistente Virtual General";

        String lower = instruccion.toLowerCase();

        if (lower.contains("java") || lower.contains("código")) {
            return "Arquitecto de Software Senior";
        }
        if (lower.contains("clima") || lower.contains("tiempo")) {
            return "Meteorólogo Profesional Certificado";
        }
        if (lower.contains("ia") || lower.contains("inteligencia")) {
            return "Profesor de Inteligencia Artificial";
        }

        return "Asistente Virtual General"; // Rol por defecto
    }

    // Agrega instrucciones extra dependiendo de lo que pida el usuario
    public String optimizarInstrucciones(String instruccion) {
        if (instruccion == null || instruccion.isBlank()) return "";

        String lower = instruccion.toLowerCase();

        if (lower.contains("java") || lower.contains("código")) {
            return instruccion + " (Por favor, proporciona el código limpio y comentado).";
        }
        if (lower.contains("paso a paso")) {
            return instruccion + " (Estructura tu respuesta en pasos numerados).";
        }

        return instruccion;
    }

    // Determina la estrategia del Prompt
    public String determinarTipoPrompt(String instruccion) {
        if (instruccion == null || instruccion.isBlank()) return "zero-shot";

        String lower = instruccion.toLowerCase();

        if (lower.contains("ejemplo") || lower.contains("formato")) {
            return "few-shot";
        }
        if (lower.contains("paso a paso") || lower.contains("razona")) {
            return "chain-of-thought";
        }

        return "zero-shot";
    }
}