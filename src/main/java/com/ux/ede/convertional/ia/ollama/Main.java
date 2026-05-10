package com.ux.ede.convertional.ia.ollama;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.context.AgenteConversacional;
import com.ux.ede.convertional.ia.ollama.context.Phi3Strategy;
import com.ux.ede.convertional.ia.ollama.context.GemmaStrategy;
import com.ux.ede.convertional.ia.ollama.context.QwenStrategy;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgenteConversacional miAgente = new AgenteConversacional();

        // Configuración única para la Prueba de Consistencia
        PromptConfig miPrompt = new PromptConfig(
                "Arquitecto de Software",
                "Explica brevemente qué es el polimorfismo en Java",
                "Usa una analogía simple"
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

        // Ejecución de la inferencia
        System.out.println("\n--- Iniciando consulta con el modelo seleccionado ---");
        miAgente.interactuar(miPrompt);
        System.out.println("--- Fin de la interacción ---\n");

        // Prueba de Consistencia: Intercambio dinámico en tiempo de ejecución
        System.out.println("Realizando comparativa con Gemma 2 para el mismo prompt...");
        miAgente.setModelo(new GemmaStrategy());
        miAgente.interactuar(miPrompt);

        scanner.close();
    }
}

