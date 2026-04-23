package com.ux.ede.convertional.ia.ollama;;

public interface InteligenciaArtificialStrategy {

    // Definimos el contrato: recibir un prompt y devolver una respuesta

    String generarRespuesta(String prompt);

    String getNombreModelo();

}
