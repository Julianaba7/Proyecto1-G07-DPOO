package PASARELAS;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GestorPasarelas {
    private Map<String, PasarelaPago> pasarelas = new HashMap<>();

    public void cargarPasarelas(String rutaArchivo) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Class<?> clase = Class.forName(linea);
                PasarelaPago pasarela = (PasarelaPago) clase.getDeclaredConstructor().newInstance();
                String nombrePasarela = clase.getSimpleName();
                pasarelas.put(nombrePasarela, pasarela);
            }
        } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public PasarelaPago obtenerPasarela(String nombre) {
        return pasarelas.get(nombre);
    }

    public Set<String> obtenerNombresPasarelas() {
        return pasarelas.keySet();
    }
}