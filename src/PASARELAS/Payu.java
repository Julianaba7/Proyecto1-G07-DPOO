package PASARELAS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Payu implements PasarelaPago {
    @Override
    public ResultadoPago procesarPago(InfoTarjeta tarjeta, InfoPago pago) {
        // Simular procesamiento de pago
        // Escribir la transacción en Payu.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\dyosh\\git\\FINALPROYECT\\Proyecto1-G07-DPOO\\src\\PASARELAS\\Payu.txt", true))) {
            writer.println("Procesando pago con Payu");
            writer.println("Monto: " + pago.getMonto());
            writer.println("Resultado: Exitoso");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultadoPago(true, "Pago procesado con Payu exitosamente");
    }

}

