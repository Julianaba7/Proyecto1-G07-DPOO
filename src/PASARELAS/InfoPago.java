package PASARELAS;

public class InfoPago {
	
	private double monto;
    private String numeroCuenta;
    private String numeroTransaccion;
    
	public InfoPago(double monto, String numeroCuenta, String numeroTransaccion) {
		this.monto = monto;
		this.numeroCuenta = numeroCuenta;
		this.numeroTransaccion = numeroTransaccion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroTransaccion() {
		return numeroTransaccion;
	}

	public void setNumeroTransaccion(String numeroTransaccion) {
		this.numeroTransaccion = numeroTransaccion;
	}
    
    
}
