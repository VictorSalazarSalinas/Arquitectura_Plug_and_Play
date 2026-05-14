package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class Phi3Strategy implements InteligenciaArtificialStrategy {

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

        // 2. Enviamos la petición real al modelo Llama3 instalado
        String jsonRespuesta = cliente.enviarPeticion("phi3", promptSeleccionado);

        /// En Phi3Strategy.java, cambia el retorno por esto:
        String respuestaLimpia = jsonRespuesta.split("\"response\":\"")[1].split("\",\"done\"")[0];
        return "Respuesta de phi3: " + respuestaLimpia.replace("\\n", "\n");
    }
    @Override
    public String getNombreModelo() {
        return "phi3";
    }


}
