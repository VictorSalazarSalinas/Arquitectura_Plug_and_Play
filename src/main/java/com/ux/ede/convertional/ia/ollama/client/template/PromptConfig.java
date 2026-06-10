package com.ux.ede.convertional.ia.ollama.client.template;

import java.util.ArrayList;
import java.util.List;

public class PromptConfig {

    private final String rol;
    private final String instrucciones;
    private final String entrada;
    private final String tipoPrompt;
    private final List<String[]> ejemplos;

    public PromptConfig(String rol, String instrucciones, String entrada, String tipoPrompt, List<String[]> ejemplos) {
        this.rol = rol;
        this.instrucciones = instrucciones;
        this.entrada = entrada;
        this.tipoPrompt = tipoPrompt;
        this.ejemplos = ejemplos == null ? new ArrayList<>() : new ArrayList<>(ejemplos);
    }

    public List<String[]> getEjemplos() {
        return ejemplos;
    }



    // Getters
    public String getRol() { return rol; }
    public String getInstrucciones() { return instrucciones; }
    public String getEntrada() { return entrada; }

    public String getTipoPrompt() { return tipoPrompt; }


}