package com.ux.ede.convertional.ia.ollama.implet;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que utiliza el patrón Builder para construir prompts dinámicos
 * siguiendo las mejores prácticas de Delimitadores y Estructura.
 */
public class PromptBuilder {

    private String rol;
    private String instrucciones;
    private List<String> ejemplos = new ArrayList<>();
    private String entradaUsuario;



    public PromptBuilder conRol(String rol) {
        this.rol = rol;
        return this;
    }

    public PromptBuilder conInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
        return this;
    }

    public PromptBuilder agregarEjemplo(String entrada, String salida) {
        this.ejemplos.add(String.format("<ejemplo>\nEntrada: %s\nSalida: %s\n</ejemplo>", entrada, salida));
        return this;
    }

    public PromptBuilder conEntrada(String entradaUsuario) {
        this.entradaUsuario = entradaUsuario;
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("<system>\n");
        sb.append("Eres un: ").append(rol).append("\n");
        sb.append("Instrucciones: ").append(instrucciones).append("\n");
        sb.append("</system>\n");

        if (!ejemplos.isEmpty()) {
            sb.append("<ejemplos>\n");
            ejemplos.forEach(e -> sb.append(e).append("\n"));
            sb.append("</examples>\n");
        }

        sb.append("<user>\n").append(entradaUsuario).append("\n</user>");

        //System.out.println(sb);

        return sb.toString();
    }
}