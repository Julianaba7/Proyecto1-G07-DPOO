package PASARELAS;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PayPal implements PasarelaPago {
    @Override
    public ResultadoPago procesarPago(InfoTarjeta tarjeta, InfoPago pago) {
        // Simular procesamiento de pago
        // Escribir la transacción en PayPal.log
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\dyosh\\git\\FINALPROYECT\\Proyecto1-G07-DPOO\\src\\PASARELAS\\PayPal.log", true))) {
            writer.println("Procesando pago con PayPal");
            writer.println("Monto: " + pago.getMonto());
            writer.println("Resultado: Exitoso");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResultadoPago(true, "Pago procesado con PayPal exitosamente");
    }

}
