package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class QwenStrategy implements InteligenciaArtificialStrategy {
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

        // Se conecta al modelo qwen2.5:3b
        String jsonRespuesta = cliente.enviarPeticion("qwen2.5:3b", promptSeleccionado);


        if (jsonRespuesta != null && jsonRespuesta.contains("\"response\":\"")) {
            String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
            return "Respuesta de Qwen 2.5: " + respuestaLimpia.replace("\\n", "\n");
        }


        System.err.println("[LOG Strategy] Respuesta inesperada del motor IA: " + jsonRespuesta);


        return "Ocurrió un problema de comunicación con Qwen 2.5. Revisa la consola para más detalles.";
    }

    @Override
    public String getNombreModelo() {
        return "qwen2.5:3b";
    }
}