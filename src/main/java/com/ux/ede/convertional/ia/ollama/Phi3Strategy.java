package com.ux.ede.convertional.ia.ollama;;


public class Phi3Strategy implements InteligenciaArtificialStrategy {

    @Override

    public String generarRespuesta(String prompt) {

        return "[Phi3-Ollama]: Respuesta ultra eficiente generada para: " + prompt;

    }

    @Override

    public String getNombreModelo() { return "Phi3"; }

}
