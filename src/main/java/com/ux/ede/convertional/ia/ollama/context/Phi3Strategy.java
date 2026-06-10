package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Phi3Strategy implements InteligenciaArtificialStrategy {

    private static final Logger LOGGER = Logger.getLogger(Phi3Strategy.class.getName());

    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {

        String promptSeleccionado = switch (config.getTipoPrompt()) {
            case "zero-shot" -> new PromptBuilder()
                    .conRol(config.getRol())
                    .conInstrucciones(config.getInstrucciones())
                    .conEntrada(config.getEntrada())
                    .build();
            case "few-shot" -> {
                PromptBuilder builder = new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(config.getInstrucciones());
                if (config.getEjemplos() != null) {
                    for (String[] ejemplo : config.getEjemplos()) {
                        builder.agregarEjemplo(ejemplo[0], ejemplo[1]);
                    }
                }
                builder.conEntrada(config.getEntrada());
                yield builder.build();
            }
            case "chain-of-thought" -> new PromptBuilder()
                    .conRol(config.getRol())
                    .conInstrucciones(config.getInstrucciones() + "\\nAnaliza el problema paso a paso antes de dar la respuesta final.")
                    .conEntrada(config.getEntrada())
                    .build();
            default -> {
                LOGGER.log(Level.WARNING, "[LOG Strategy] Tipo de prompt desconocido: {0}. Usando zero-shot por defecto.", config.getTipoPrompt());
                yield new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(config.getInstrucciones())
                        .conEntrada(config.getEntrada())
                        .build();
            }
        };

        String jsonRespuesta = cliente.enviarPeticion("phi3", promptSeleccionado);

        if (jsonRespuesta != null && jsonRespuesta.contains("\"response\":\"")) {
            String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
            return "Respuesta de phi3: " + respuestaLimpia.replace("\\n", "\n");
        }

        LOGGER.log(Level.SEVERE, "[LOG Strategy] Respuesta inesperada del motor IA (Phi-3): {0}", jsonRespuesta);

        return "Ocurrió un problema de comunicación con Phi-3. Revisa la consola para más detalles.";
    }

    @Override
    public String getNombreModelo() {
        return "phi3";
    }
}
