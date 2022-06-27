package net.kranki.udemyApp.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

/**
 * This class if of props for saveName of file and number random
 *
 * @author Oscar Rodriguez
 */
public class Utileria {


    /**
     * This method is for change name of files to upload in the system
     *
     * @param multiPart The file that you want to upload.
     * @param ruta      The path where the file will be saved.
     * @return The name of the file.
     */
    public static String guardarArchivo(MultipartFile multiPart, String ruta) {
        // Obtenemos el nombre original del archivo.
        String nombreOriginal = multiPart.getOriginalFilename();
        nombreOriginal = nombreOriginal.replace(" ", "-");
        String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
        try {
            // Formamos el nombre del archivo para guardarlo en el disco duro.
            File imageFile = new File(ruta + nombreFinal);
            System.out.println("Archivo: " + imageFile.getAbsolutePath());
            // Guardamos fisicamente el archivo en HD.
            multiPart.transferTo(imageFile);
            return nombreFinal;
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            return null;
        }
    }


    /**
     * this method created a character special for insert in guardararchivo
     *
     * @param count The length of the random string you want to create.
     * @return A random string of characters.
     */
    private static String randomAlphaNumeric(int count) {
        String Caracteres = "ABCDEFGHIJKLMNOPQRSTWXYZ";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int caracter = (int) (Math.random() * Caracteres.length());
            builder.append(Caracteres.charAt(caracter));
        }
        return builder.toString();
    }
}
