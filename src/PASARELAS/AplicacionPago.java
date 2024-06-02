package PASARELAS;

import java.util.Scanner;
import java.util.Set;

public class AplicacionPago {
    public static void main(String[] args) {
        try {
            GestorPasarelas gestor = new GestorPasarelas();
            gestor.cargarPasarelas("C:\\Users\\dyosh\\git\\FINALPROYECT\\Proyecto1-G07-DPOO\\src\\PASARELAS\\configPasarelas.txt");

            Scanner scanner = new Scanner(System.in);

            System.out.println("Seleccione una pasarela de pago:");
            Set<String> nombresPasarelas = gestor.obtenerNombresPasarelas();
            int i = 1;
            for (String nombre : nombresPasarelas) {
                System.out.println(i + ". " + nombre);
                i++;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            String nombrePasarela = nombresPasarelas.toArray(new String[0])[opcion - 1];
            PasarelaPago pasarela = gestor.obtenerPasarela(nombrePasarela);

            System.out.println("Ingrese la información de la tarjeta:");
            System.out.print("Número de tarjeta: ");
            String numeroTarjeta = scanner.nextLine();
            System.out.print("Nombre del titular: ");
            String nombreTitular = scanner.nextLine();
            System.out.print("Fecha de expiración (MM/AA): ");
            String fechaExpiracion = scanner.nextLine();
            System.out.print("Código de seguridad: ");
            String codigoSeguridad = scanner.nextLine();

            InfoTarjeta tarjeta = new InfoTarjeta(numeroTarjeta, nombreTitular, fechaExpiracion, codigoSeguridad);

            System.out.println("Ingrese la información del pago:");
            System.out.print("Monto: ");
            double monto = scanner.nextDouble();
            scanner.nextLine();  // Limpiar el buffer
            System.out.print("Número de cuenta: ");
            String numeroCuenta = scanner.nextLine();
            System.out.print("Número de transacción: ");
            String numeroTransaccion = scanner.nextLine();

            InfoPago pago = new InfoPago(monto, numeroCuenta, numeroTransaccion);

            ResultadoPago resultado = pasarela.procesarPago(tarjeta, pago);
            System.out.println("Resultado del pago: " + resultado.getMensaje());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
