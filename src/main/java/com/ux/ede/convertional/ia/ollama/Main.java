package com.ux.ede.convertional.ia.ollama;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.context.AgenteConversacional;
import com.ux.ede.convertional.ia.ollama.context.Phi3Strategy;
import com.ux.ede.convertional.ia.ollama.context.GemmaStrategy;
import com.ux.ede.convertional.ia.ollama.context.QwenStrategy;
import com.ux.ede.convertional.ia.ollama.routing.IntentRouter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgenteConversacional miAgente = new AgenteConversacional();
        IntentRouter router = new IntentRouter();

        String promptPrueba = "Explica qué es el polimorfismo en Java. Piensa paso a paso.";

        // 2. Usamos la lógica de objetos (Router) para deducir las propiedades
        String rolDetectado = router.determinarRol(promptPrueba);
        String instruccionesOptimizadas = router.optimizarInstrucciones(promptPrueba);
        String tipoDePrompt = router.determinarTipoPrompt(promptPrueba);

        // 3. Rellenamos la Configuración
        PromptConfig miPrompt = new PromptConfig(
                rolDetectado,               // Ej: "Arquitecto de Software Senior"
                instruccionesOptimizadas,   // Ej: Agregará que dé código limpio
                promptPrueba,               // El input original
                tipoDePrompt,               // Ej: "chain-of-thought" (porque dice paso a paso)
                new ArrayList<>()           // Lista de ejemplos vacía para no complicarlo
        );

        System.out.println("--- Selector de Cerebro de IA ---");
        System.out.println("1. Phi-3 (Mini)");
        System.out.println("2. Gemma 2 (2B)");
        System.out.println("3. Qwen 2.5 (3B)");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();

        // Aplicando el patrón Strategy mediante un Switch
        switch (opcion) {
            case 1:
                miAgente.setModelo(new Phi3Strategy());
                break;
            case 2:
                miAgente.setModelo(new GemmaStrategy());
                break;
            case 3:
                miAgente.setModelo(new QwenStrategy());
                break;
            default:
                System.out.println("Opción no válida, usando modelo por defecto (Phi3).");
                miAgente.setModelo(new Phi3Strategy());
        }

        // Ejecución
        System.out.println("\n--- Resumen del Enrutamiento ---");
        System.out.println("Rol asignado: " + miPrompt.getRol());
        System.out.println("Tipo de Prompt: " + miPrompt.getTipoPrompt());

        System.out.println("\n--- Iniciando consulta con el modelo seleccionado ---");
        miAgente.interactuar(miPrompt);
        System.out.println("--- Fin de la interacción ---\n");

        scanner.close();
    }
}

