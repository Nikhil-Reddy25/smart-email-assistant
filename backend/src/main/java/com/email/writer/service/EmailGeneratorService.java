package com.email.writer.service;

import com.email.writer.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class EmailGeneratorService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public EmailGeneratorService() {
        this.webClient = WebClient.builder().build();
    }

    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt = buildPrompt(emailRequest);
        Map<String, Object> requestBody = createRequestBody(prompt);
        
        String response = callGeminiAPI(requestBody);
        return extractTextFromResponse(response);
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. ");
        prompt.append("Please don't generate a subject line. ");
        
        if (emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }
        
        prompt.append("\n\nOriginal email:\n");
        prompt.append(emailRequest.getEmailContent());
        
        return prompt.toString();
    }

    private Map<String, Object> createRequestBody(String prompt) {
        return Map.of(
            "contents", List.of(
                Map.of(
                    "parts", List.of(
                        Map.of("text", prompt)
                    )
                )
            )
        );
    }

    private String callGeminiAPI(Map<String, Object> requestBody) {
        String urlWithKey = geminiApiUrl + "?key=" + geminiApiKey;
        
        Mono<String> responseMono = webClient.post()
                .uri(urlWithKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
        
        return responseMono.block();
    }

    private String extractTextFromResponse(String response) {
        try {
            int textIndex = response.indexOf("\"text\"");
            if (textIndex != -1) {
                int startQuote = response.indexOf("\"", textIndex + 7);
                int endQuote = response.indexOf("\"", startQuote + 1);
                if (startQuote != -1 && endQuote != -1) {
                    String extracted = response.substring(startQuote + 1, endQuote);
                    return extracted.replace("\\n", "\n");
                }
            }
            return "Failed to extract response";
        } catch (Exception e) {
            return "Error parsing response: " + e.getMessage();
        }
    }
}
