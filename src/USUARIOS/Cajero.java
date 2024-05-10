package USUARIOS;
import java.util.ArrayList;
import java.util.List;

import PAGOS.Pago;
import PIEZAS.PiezaExhibicion;

public class Cajero extends Usuario {
	
	private static List<Pago> pagos;

    public Cajero(String id, String nombre, String login, String password) {
        super(id, nombre, login, password);
        this.rol = RolUsuarios.CAJERO;
        Cajero.pagos = new ArrayList<Pago>();
    }

    public static void procesarPago(Pago pago) {
        if (!pago.isConfirmado()) {
            pago.confirmarPago();
            pagos.add(pago);
        }
    }

	public static List<Pago> getPagos() {
		return pagos;
	}

	public static void setPagos(List<Pago> pagos) {
		Cajero.pagos = pagos;
	}
    
    
}
