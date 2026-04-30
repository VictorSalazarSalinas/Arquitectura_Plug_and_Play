package com.ux.ede.convertional.ia.ollama.routing;

public class IntentRouter {

    // Este metodo analiza las instrucciones que el usuario metió en el Main
    public String determinarRol(String instruccionesUsuario) {
        String input = instruccionesUsuario.toLowerCase();

        if (input.contains("clima") || input.contains("tiempo")) {
            return "Meteorólogo Profesional Certificado";
        }
        if (input.contains("patrón") || input.contains("código") || input.contains("java")) {
            return "Arquitecto de Software Senior y experto en Clean Code";
        }
        if (input.contains("tarea") || input.contains("explica")) {
            return "Profesor de Inteligencia Artificial";
        }

        return "Asistente Virtual General";
    }

    // Aquí podrías incluso determinar si necesita Delimitadores o no
    public String optimizarInstrucciones(String instrucciones) {
        // Si es clima, le agregamos que use un formato específico
        if (instrucciones.toLowerCase().contains("clima")) {
            return instrucciones + " (Responde solo con la temperatura y condición)";
        }
        // si es sobre Inteligencia artificial
        if (instrucciones.toLowerCase().contains("inteligencia artificial") || instrucciones.toLowerCase().contains("ia")) {
            return instrucciones + " (Explica con ejemplos y analogías)";
        }

        // si es sobre videojuegos
        if (instrucciones.toLowerCase().contains("videojuegos") || instrucciones.toLowerCase().contains("gaming")) {
            return instrucciones + " (Incluye referencias a juegos populares)";
        }


        return instrucciones;
    }
}