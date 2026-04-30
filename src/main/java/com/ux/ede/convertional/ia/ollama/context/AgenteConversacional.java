package com.ux.ede.convertional.ia.ollama.context;

import com.ux.ede.convertional.ia.ollama.client.template.PromptConfig;
import com.ux.ede.convertional.ia.ollama.implet.InteligenciaArtificialStrategy;
import com.ux.ede.convertional.ia.ollama.routing.IntentRouter;

public class AgenteConversacional {

    private InteligenciaArtificialStrategy modelo;
    private IntentRouter router = new IntentRouter();

    // El corazón del patrón: inyección de la estrategia

    public void setModelo(InteligenciaArtificialStrategy nuevoModelo) {

        this.modelo = nuevoModelo;

        System.out.println("Cambiando cerebro a: " + nuevoModelo.getNombreModelo());

    }

    public void interactuar(PromptConfig config) {
        if (modelo == null) {
            System.err.println("Error: No hay un modelo de IA cargado.");
            return;
        }

        // 1. El Router analiza y optimiza (Mantenemos tu lógica intacta)
        String rolDetectado = router.determinarRol(config.getEntrada());
        String mensajeOptimizado = router.optimizarInstrucciones(config.getEntrada());

        System.out.println("-> Rol detectado: " + rolDetectado);

        // 2. LA MAGIA: Creamos un NUEVO objeto con la información ya procesada por el Router
        PromptConfig configListaParaIA = new PromptConfig(
                rolDetectado,               // El rol que descubrió el router
                config.getInstrucciones(),  // Las instrucciones originales
                mensajeOptimizado           // El mensaje ya optimizado
        );

        // 3. Ahora sí, le pasamos el OBJETO al modelo, como lo exige la interfaz
        String respuesta = modelo.generarRespuesta(configListaParaIA);

        System.out.println(respuesta);
    }

}