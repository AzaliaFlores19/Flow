/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor_musicaa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author GPS
 */


public class SongList {
 private static final String CARPETA_GUARDADO = "Reproductor_MusicaA";
    private static final String LISTA_REPRODUCCION = CARPETA_GUARDADO + File.separator + "playlist.txt";

    JFileChooser escojerF = new JFileChooser();

    public ArrayList<File> listaS = new ArrayList<>();

    // Constructor
    public SongList() {
        crearCarpeta();
        cargarCancionesGuardadas();
    }

    public void cargarCancionesGuardadas() {
        File listaReproduccion = new File(LISTA_REPRODUCCION);
        if (listaReproduccion.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(listaReproduccion))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    File cancion = new File(linea);
                    if (cancion.exists()) {
                        listaS.add(cancion);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void guardarCanciones() {
        File listaReproduccion = new File(LISTA_REPRODUCCION);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(listaReproduccion))) {
            for (File cancion : listaS) {
                writer.write(cancion.getAbsolutePath());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarCancion(File file) {
      String fileName = file.getName();
    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
    // Verifica si la extension es .mp3 o .wav 
    if (fileExtension.equalsIgnoreCase("mp3") || fileExtension.equalsIgnoreCase("wav")) {
        // Agregar el archivo a la lista de reproducción
        if (!listaS.contains(file)) {
            listaS.add(file);
            guardarCanciones();
        }
    } else {
        // Error en archivo
        JOptionPane.showMessageDialog(null, "Seleccione un archivo con extensión .mp3 o .wav", "Error de formato", JOptionPane.ERROR_MESSAGE);
    }
    }

    public ArrayList<File> getPlayList() {
        return listaS;
    }
    //crear carpetda donde van las canciones
    public void crearCarpeta() {
        File carpeta = new File(CARPETA_GUARDADO);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
    }
    
}
