package com.ux.ede.convertional.ia.ollama;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.context.AgenteConversacional;

import com.ux.ede.convertional.ia.ollama.context.Phi3Strategy;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.implet.PromptBuilder;
import com.ux.ede.convertional.ia.ollama.routing.IntentRouter;


public class Main {

    public static void main(String[] args) {

        AgenteConversacional miAgente = new AgenteConversacional();

        // 1. Usamos Llama3 para una tarea compleja

        //miAgente.setModelo(new Llama3Strategy());
//
        //miAgente.interactuar("Explica la física cuántica.");
//
        //System.out.println("---");
//
        //// 2. Cambiamos a Mistral en tiempo de ejecución por su rapidez
//
        //miAgente.setModelo(new MistralStrategy());
//
        //miAgente.interactuar("Explica un chiste corto.");
//
        //// 3. Cambiamos a Phi3 en tiempo de ejecución ultra eficiente





        IntentRouter router = new IntentRouter();

        // Lo que el usuario realmente quiere
        String loQuePidioElUsuario = "Explica el patrón Strategy de forma sencilla";

        // El Router hace su magia basada en el texto del usuario
        String rolDetectado = router.determinarRol(loQuePidioElUsuario);
        String instruccionesMejoradas = router.optimizarInstrucciones(loQuePidioElUsuario);
        PromptConfig miPrompt = new PromptConfig(
                rolDetectado,
                instruccionesMejoradas,
                "Explícamelo como experto en el área" // La pregunta específica
        );

        miAgente.setModelo(new Phi3Strategy());

        miAgente.interactuar(miPrompt);

    }

    public void version1AgenteConversacional() {

        AgenteConversacional miAgente = new AgenteConversacional();

        // 1. Configuramos la estrategia de Llama3 (que ya tiene el cliente HTTP)
        Phi3Strategy miLlama = new Phi3Strategy();
        miAgente.setModelo(miLlama);

        // Creamos el "objeto para el prompt" con la configuración deseada
        PromptConfig miPrompt = new PromptConfig(
                "Arquitecto de Software Senior",
                "Explica el patrón Strategy de forma sencilla",
                "¿Qué es y para qué sirve?"
        );

        // 2. Configuramos la instrucción específica (Aquí se manda a llamar)
        // El usuario solo manda un texto simple, la arquitectura se encarga del resto
        System.out.println("--- Iniciando conversación con IA Local ---");
        miAgente.interactuar(miPrompt);

        System.out.println("--- Fin de la interacción ---");
    }




}


