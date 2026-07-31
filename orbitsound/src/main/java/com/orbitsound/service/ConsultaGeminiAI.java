package com.orbitsound.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;

public class ConsultaGeminiAI {

    public static String obterInformacao(String texto) {
        String apiKey = System.getenv("GOOGLE_API_KEY");

            Client client = Client.builder()
                    .apiKey(System.getenv("GOOGLE_API_KEY"))
                    .build();

            GenerateContentConfig config = GenerateContentConfig.builder()
                    .maxOutputTokens(1000)
                    .build();

            GenerateContentResponse response = client.models.generateContent(
                    "gemini-3.5-flash-lite",
                    "Me fale sobre o artista: " + texto,
                    config
            );

            return response.text();
    }
}
