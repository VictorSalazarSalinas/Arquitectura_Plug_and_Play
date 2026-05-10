package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class QwenStrategy implements InteligenciaArtificialStrategy {
    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {
        String promptFinal = new PromptBuilder()
                .conRol(config.getRol())
                .conInstrucciones(config.getInstrucciones())
                .conEntrada(config.getEntrada())
                .build();

        // Se conecta al modelo qwen2.5:3b
        String jsonRespuesta = cliente.enviarPeticion("qwen2.5:3b", promptFinal);

        String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
        return "Respuesta de Qwen 2.5: " + respuestaLimpia.replace("\\n", "\n");
    }

    @Override
    public String getNombreModelo() {
        return "qwen2.5:3b";
    }
}