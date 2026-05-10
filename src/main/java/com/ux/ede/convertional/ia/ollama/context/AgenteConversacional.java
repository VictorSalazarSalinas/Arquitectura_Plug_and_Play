package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.routing.IntentRouter;

public class AgenteConversacional {

    private InteligenciaArtificialStrategy modelo;
    private IntentRouter router = new IntentRouter();

    // El corazón del patrón: inyección de la estrategia

    public void setModelo(InteligenciaArtificialStrategy nuevoModelo) {

        this.modelo = nuevoModelo;

        System.out.println("Cambiando cerebro a: " + nuevoModelo.getNombreModelo());

    }

    public void interactuar(PromptConfig config) {
        if (modelo == null) {
            System.err.println("Error: No hay un modelo de IA cargado.");
            return;
        }
        String respuesta = modelo.generarRespuesta(config);

        System.out.println(respuesta);
    }

}