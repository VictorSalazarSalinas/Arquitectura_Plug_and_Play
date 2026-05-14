package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class GemmaStrategy implements InteligenciaArtificialStrategy {
    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {
        //Determinar el Prompt seleccionado
        String promptSeleccionado = "";

        //Swtich para determinar el tipo de prompt de acuerdo al valor del objeto config
        switch (config.getTipoPrompt()) {
            case "zero-shot":
                promptSeleccionado = new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(config.getInstrucciones())
                        .conEntrada(config.getEntrada())
                        .build();
                break;
            case "few-shot":
                PromptBuilder builder = new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(config.getInstrucciones());
                if (config.getEjemplos() != null) {
                    for (String[] ejemplo : config.getEjemplos()) {
                        builder.agregarEjemplo(ejemplo[0], ejemplo[1]);
                    }
                }
                builder.conEntrada(config.getEntrada());
                promptSeleccionado = builder.build();
                break;

            case "chain-of-thought":
                /*
                * Para este tipo de prompt, podríamos agregar
                  instrucciones específicas para que el modelo piense paso a paso
                */
                promptSeleccionado = new PromptBuilder()
                        .conRol(config.getRol())
                        .conInstrucciones(config.getInstrucciones() + "\\nAnaliza el problema paso a paso antes de dar la respuesta final.")
                        .conEntrada(config.getEntrada())
                        .build();
                break;
        }

        // Se conecta al modelo gemma2:2b
        String jsonRespuesta = cliente.enviarPeticion("gemma2:2b", promptSeleccionado);

        String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
        return "Respuesta de Gemma 2: " + respuestaLimpia.replace("\\n", "\n");
    }

    @Override
    public String getNombreModelo() {
        return "gemma2:2b";
    }
}