package com.ux.ede.convertional.ia.ollama;

public class PromptBuilder {

    public static String mejorarPrompt(String inputUsuario) {
        StringBuilder sb = new StringBuilder();

        sb.append("MODO_SISTEMA: Eres un agente de IA experto, conciso y analítico. ");
        sb.append("Tu objetivo es proporcionar respuestas técnicas pero fáciles de entender. ");
        sb.append("\n\nCONSULTA DEL USUARIO: ");

        // Few-shot examples
        sb.append("EJEMPLOS:\n");

        sb.append("Usuario: ¿Qué es la POO?\n");
        sb.append("Respuesta: La Programación Orientada a Objetos es un paradigma basado en clases y objetos que promueve reutilización y modularidad.\n\n");

        sb.append("Usuario: Explica herencia en Java\n");
        sb.append("Respuesta: La herencia permite que una clase hija reutilice atributos y métodos de una clase padre mediante 'extends'.\n\n");

        // Insertar el prompt

        sb.append("CONSULTA DEL USUARIO:\n");
        sb.append(inputUsuario);

        return sb.toString();
    }
}