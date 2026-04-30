package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.OllamaClient;
import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;

public class Phi3Strategy implements InteligenciaArtificialStrategy {

    private final OllamaClient cliente = new OllamaClient();

    @Override
    public String generarRespuesta(PromptConfig config) {

        // 1. Construimos el prompt profesional con la técnica elegida
        String promptFinal = new PromptBuilder()
                .conRol(config.getRol())
                .conInstrucciones(config.getInstrucciones())
                .conEntrada(config.getEntrada())
                .build();

        // 2. Enviamos la petición real al modelo Llama3 instalado
        String jsonRespuesta = cliente.enviarPeticion("phi3", promptFinal);

        // Tip para los alumnos: Aquí deberían usar Jackson/Gson para extraer solo el campo "response"
        return "Respuesta de phi3: " + jsonRespuesta;
    }




    @Override
    public String getNombreModelo() {
        return "Phi3-Local-Ryzen5";
    }


}
