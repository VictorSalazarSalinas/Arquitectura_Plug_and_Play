package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import java.util.logging.Logger;

public class AgenteConversacional {

    private static final Logger LOGGER = Logger.getLogger(AgenteConversacional.class.getName());

    private InteligenciaArtificialStrategy modelo;

    public void setModelo(InteligenciaArtificialStrategy nuevoModelo) {
        this.modelo = nuevoModelo;

       LOGGER.info("Cambiando cerebro a: " + nuevoModelo.getNombreModelo());
    }

    public void interactuar(PromptConfig config) {
        if (modelo == null) {
            LOGGER.severe("Error: No hay un modelo de IA cargado.");
            return;
        }

        String respuesta = modelo.generarRespuesta(config);

        LOGGER.info(respuesta);
    }
}