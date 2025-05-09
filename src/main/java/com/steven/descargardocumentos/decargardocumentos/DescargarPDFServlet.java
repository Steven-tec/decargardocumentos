// Declaración del paquete donde se encuentra la clase (paquete raíz del proyecto).
package com.steven.descargardocumentos.decargardocumentos;

// Importación de clases necesarias para manejar Servlets, peticiones HTTP, y generación de PDFs con iText.
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

// Anotación que indica que esta clase es un Servlet que responde a solicitudes HTTP en la URL "/descargar-pdf".
@WebServlet("/descargar-pdf")
public class DescargarPDFServlet extends HttpServlet {

    // Método que maneja las solicitudes HTTP GET cuando el usuario accede a la URL "/descargar-pdf".
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Establecer el tipo de contenido de la respuesta como un archivo PDF.
        // Esto le indica al navegador que la respuesta es un archivo PDF y que debe abrirlo como tal.
        response.setContentType("application/pdf");

        // Establecer el encabezado "Content-Disposition" para indicar al navegador que debe descargar el archivo
        // con el nombre "archivo.pdf" cuando se haga la solicitud.
        response.setHeader("Content-Disposition", "attachment; filename=\"archivo.pdf\"");

        // Intentar generar el archivo PDF.
        try {
            // Crear un nuevo documento PDF.
            Document document = new Document();

            // Crear un escritor de PDF que se encargará de escribir el contenido del documento en la salida HTTP.
            PdfWriter.getInstance(document, response.getOutputStream());

            // Abrir el documento para agregar contenido.
            document.open();

            // Agregar un párrafo al documento con el texto "Este es un archivo PDF generado en Jakarta EE.".
            document.add(new Paragraph("Este es un archivo PDF generado en Jakarta EE."));

            // Cerrar el documento, lo que finaliza la escritura del PDF.
            document.close();
        } catch (DocumentException e) {
            // Si ocurre un error mientras se genera el PDF, lanzar una excepción de tipo IOException.
            // El mensaje de error será "Error al generar PDF" y se incluirá la excepción original (e).
            throw new IOException("Error al generar PDF", e);
        }
    }
}

