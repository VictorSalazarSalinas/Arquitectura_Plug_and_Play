package com.ux.ede.convertional.ia.ollama;

public class AgenteConversacional {

    private InteligenciaArtificialStrategy modelo;

    // El corazón del patrón: inyección de la estrategia

    public void setModelo(InteligenciaArtificialStrategy nuevoModelo) {

        this.modelo = nuevoModelo;

        System.out.println("Cambiando cerebro a: " + nuevoModelo.getNombreModelo());

    }

    public void interactuar(String mensaje) {

        if (modelo == null) {

            System.err.println("Error: No hay un modelo de IA cargado.");

            return;

        }

        System.out.println(modelo.generarRespuesta(mensaje));

    }

}