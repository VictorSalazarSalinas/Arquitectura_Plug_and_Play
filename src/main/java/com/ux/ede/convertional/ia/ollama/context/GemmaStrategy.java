package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class GemmaStrategy implements InteligenciaArtificialStrategy {
    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {
        String promptFinal = new PromptBuilder()
                .conRol(config.getRol())
                .conInstrucciones(config.getInstrucciones())
                .conEntrada(config.getEntrada())
                .build();

        // Se conecta al modelo gemma2:2b
        String jsonRespuesta = cliente.enviarPeticion("gemma2:2b", promptFinal);

        String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
        return "Respuesta de Gemma 2: " + respuestaLimpia.replace("\\n", "\n");
    }

    @Override
    public String getNombreModelo() {
        return "gemma2:2b";
    }
}