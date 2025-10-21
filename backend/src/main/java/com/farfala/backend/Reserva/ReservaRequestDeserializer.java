package com.farfala.backend.Reserva;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deserializador personalizado que acepta hora en formato "HH:mm" o "HH:mm:ss"
 * sin causar errores de parseo.
 */
public class ReservaRequestDeserializer extends JsonDeserializer<ReservaRequest> {

    @Override
    public ReservaRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        ReservaRequest req = new ReservaRequest();

        // Leer claseId y horarioId
        if (node.has("claseId")) {
            req.setClaseId(node.get("claseId").asLong());
        }
        if (node.has("horarioId")) {
            req.setHorarioId(node.get("horarioId").asLong());
        }

        // Leer fecha
        if (node.has("fecha")) {
            req.setFecha(LocalDate.parse(node.get("fecha").asText()));
        }

        // Leer hora con tolerancia a "HH:mm" y "HH:mm:ss"
        if (node.has("hora")) {
            String horaStr = node.get("hora").asText().trim();
            try {
                req.setHora(LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm:ss")));
            } catch (DateTimeParseException e1) {
                try {
                    req.setHora(LocalTime.parse(horaStr, DateTimeFormatter.ofPattern("HH:mm")));
                } catch (DateTimeParseException e2) {
                    throw new IOException("Formato de hora inválido: " + horaStr);
                }
            }
        }

        return req;
    }
}
