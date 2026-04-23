package com.ux.ede.convertional.ia.ollama;;

// Estrategia 1: Llama3 (Pensado para razonamiento)

public class Llama3Strategy implements InteligenciaArtificialStrategy {

    @Override

    public String generarRespuesta(String prompt) {

        return "[Llama3-Ollama]: Analizando con 8B parámetros... " + prompt;

    }

    @Override

    public String getNombreModelo() { return "Llama3"; }

}