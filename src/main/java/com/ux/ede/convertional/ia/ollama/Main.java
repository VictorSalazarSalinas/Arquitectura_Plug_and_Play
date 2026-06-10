package com.ux.ede.convertional.ia.ollama;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.context.AgenteConversacional;
import com.ux.ede.convertional.ia.ollama.context.Phi3Strategy;
import com.ux.ede.convertional.ia.ollama.context.GemmaStrategy;
import com.ux.ede.convertional.ia.ollama.context.QwenStrategy;
import com.ux.ede.convertional.ia.ollama.routing.IntentRouter;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    // Instanciamos el Logger para la clase principal
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    // Suprimimos la advertencia de 'args' sin uso para mantener la compatibilidad con Java 17
    public static void main(@SuppressWarnings("unused") String[] args) {
        Scanner scanner = new Scanner(System.in);
        AgenteConversacional miAgente = new AgenteConversacional();
        IntentRouter router = new IntentRouter();

        String promptPrueba = "Explica qué es el polimorfismo en Java. Piensa paso a paso.";

        PromptConfig miPrompt = prepararPromptConfig(router, promptPrueba);

        LOGGER.info("--- Selector de Cerebro de IA ---");
        LOGGER.info("1. Phi-3 (Mini)");
        LOGGER.info("2. Gemma 2 (2B)");
        LOGGER.info("3. Qwen 2.5 (3B)");

        LOGGER.info("Seleccione una opción: ");

        int opcion = scanner.nextInt();

        // Aplicando el patrón Strategy mediante un Switch (usando Switch normal por el break)
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
                LOGGER.warning("Opción no válida, usando modelo por defecto (Phi3).");
                miAgente.setModelo(new Phi3Strategy());
        }

        // Ejecución
        LOGGER.info("\n--- Resumen del Enrutamiento ---");
        // Nota: Si cambiaste PromptConfig a un 'record', recuerda cambiar .getRol() por .rol()
        LOGGER.info("Rol asignado: " + miPrompt.getRol());
        LOGGER.info("Tipo de Prompt: " + miPrompt.getTipoPrompt());

        LOGGER.info("\n--- Iniciando consulta con el modelo seleccionado ---");
        miAgente.interactuar(miPrompt);
        LOGGER.info("--- Fin de la interacción ---\n");

        scanner.close();
    }


    private static PromptConfig prepararPromptConfig(IntentRouter router, String promptPrueba) {
        String rolDetectado = router.determinarRol(promptPrueba);
        String instruccionesOptimizadas = router.optimizarInstrucciones(promptPrueba);
        String tipoDePrompt = router.determinarTipoPrompt(promptPrueba);

        return new PromptConfig(
                rolDetectado,
                instruccionesOptimizadas,
                promptPrueba,
                tipoDePrompt,
                new ArrayList<>()
        );
    }
}