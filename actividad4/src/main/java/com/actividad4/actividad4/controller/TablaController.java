package com.actividad4.actividad4.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TablaController {

    @GetMapping("/tabla")
    public String generarTabla(
            @RequestParam(required = false) String filas,
            @RequestParam(required = false) String columnas) {

        int numeroFilas = 1;
        if (filas != null && !filas.trim().isEmpty()) {
            try {
                numeroFilas = Integer.parseInt(filas.trim());
                if (numeroFilas < 1) numeroFilas = 1;
                if (numeroFilas > 20) numeroFilas = 20;
            } catch (NumberFormatException e) {
                numeroFilas = 1;
            }
        }

        int numeroColumnas = 1;
        if (columnas != null && !columnas.trim().isEmpty()) {
            try {
                numeroColumnas = Integer.parseInt(columnas.trim());
                if (numeroColumnas < 1) numeroColumnas = 1;
                if (numeroColumnas > 20) numeroColumnas = 20;
            } catch (NumberFormatException e) {
                numeroColumnas = 1;
            }
        }

        String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Tabla</title>" +
                "<style>table{border-collapse:collapse;}th,td{border:1px solid black;padding:8px;}</style>" +
                "</head><body><table><thead><tr>";

        for (int col = 1; col <= numeroColumnas; col++) {
            html += "<th>Columna " + col + "</th>";
        }

        html += "</tr></thead><tbody>";

        for (int fila = 1; fila <= numeroFilas; fila++) {
            html += "<tr>";
            for (int col = 1; col <= numeroColumnas; col++) {
                html += "<td>Fila " + fila + ", Columna " + col + "</td>";
            }
            html += "</tr>";
        }

        html += "</tbody></table></body></html>";

        return html;
    }
}