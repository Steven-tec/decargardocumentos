// Declaración del paquete donde se encuentra la clase (paquete raíz del proyecto).
package com.steven.descargardocumentos.decargardocumentos;

// Importación de clases necesarias para el uso de Servlets, manejo de peticiones HTTP y creación de archivos Excel.
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Anotación que indica que esta clase es un Servlet y que responde a las solicitudes en la URL "/descargar-excel".
@WebServlet("/descargar-excel")
public class DescargarExcelServlet extends HttpServlet {

    // Método que maneja las solicitudes HTTP GET (cuando el usuario accede a la URL "/descargar-excel").
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Establecer el tipo de contenido de la respuesta como un archivo Excel (XLSX).
        // Esto le indica al navegador que la respuesta debe ser tratada como un archivo Excel descargable.
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Establecer el encabezado "Content-Disposition", que le indica al navegador que descargue el archivo con el nombre "archivo.xlsx".
        response.setHeader("Content-Disposition", "attachment; filename=\"archivo.xlsx\"");

        // Intentamos crear un archivo Excel usando Apache POI
        try (Workbook workbook = new XSSFWorkbook()) {
            // Crear una nueva hoja dentro del libro de trabajo Excel con el nombre "Datos".
            Sheet sheet = workbook.createSheet("Datos");

            // Crear la primera fila en la hoja de Excel.
            Row row = sheet.createRow(0);

            // Crear dos celdas en la primera fila (índice 0) y asignarles valores.
            // La celda 0 contendrá el texto "Mensaje".
            row.createCell(0).setCellValue("Mensaje");
            // La celda 1 contendrá el texto "Este es un archivo Excel generado en Jakarta EE.".
            row.createCell(1).setCellValue("Este es un archivo Excel generado en Jakarta EE.");

            // Escribir el contenido del archivo Excel en el flujo de salida de la respuesta HTTP.
            // Esto hará que el archivo Excel se envíe directamente como respuesta al navegador del usuario.
            workbook.write(response.getOutputStream());

        } catch (Exception e) {
            // Si ocurre algún error durante el proceso de creación del archivo Excel, capturamos la excepción.
            // Se lanza una nueva IOException, que contiene el mensaje "Error al generar el archivo Excel" y la excepción original.
            throw new IOException("Error al generar el archivo Excel", e);
        }
    }
}



