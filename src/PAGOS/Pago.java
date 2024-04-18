package PAGOS;

public class Pago {
    private double monto;
    private MetodoPago metodoPago;//PUEDE SER ENUM
    private boolean confirmado;

    public Pago(double monto, MetodoPago metodoPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.confirmado = false;
    }

    public void confirmarPago() {
        this.confirmado = true;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public double getMonto() {
        return monto;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }
}
