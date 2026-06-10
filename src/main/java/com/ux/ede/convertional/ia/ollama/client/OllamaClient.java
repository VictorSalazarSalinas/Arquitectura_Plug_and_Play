package com.ux.ede.convertional.ia.ollama.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OllamaClient {

    private static final Logger LOGGER = Logger.getLogger(OllamaClient.class.getName());

    private static final String URL_API = "http://localhost:11434/api/generate";

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public String enviarPeticion(String modelo, String promptEstructurado) {

       String jsonBody = String.format(
                "{\"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false, \"options\": {\"stop\": [\"<user>\", \"\\n\\n\"]}}",
                modelo, promptEstructurado.replace("\"", "\\\"").replace("\n", "\\n")
        );

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .timeout(Duration.ofSeconds(30))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "[LOG OllamaClient] ¡Excepción de I/O atrapada al conectar con Ollama!", e);
            return "Error de conexión: " + e.getMessage();

        } catch (InterruptedException e) {
            LOGGER.log(Level.WARNING, "[LOG OllamaClient] La petición a Ollama fue interrumpida.", e);

            Thread.currentThread().interrupt();

            return "Error: Petición interrumpida.";
        }
    }
}