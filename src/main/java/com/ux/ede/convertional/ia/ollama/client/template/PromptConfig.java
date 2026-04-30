package com.ux.ede.convertional.ia.ollama.client.template;

public class PromptConfig {

    private String rol;
    private String instrucciones;
    private String entrada;

    public PromptConfig(String rol, String instrucciones, String entrada) {
        this.rol = rol;
        this.instrucciones = instrucciones;
        this.entrada = entrada;
    }

    // Getters
    public String getRol() { return rol; }
    public String getInstrucciones() { return instrucciones; }
    public String getEntrada() { return entrada; }
}

