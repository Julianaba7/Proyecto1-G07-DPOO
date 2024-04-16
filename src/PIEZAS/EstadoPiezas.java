package PIEZAS;


//COMPRA Y SUBASTA DE PIEZAS: Hay 3 posibles estados:
	//Dispobible: Si la pieza puede ser vendida por un valor fijo
	//Bloqueada: Si la pieza no se puede vender (Ya sea por que no se puede vender por un valor fijo, o un comprador está en revisión)
	//Vendida: Si la pieza ya se vendió
public enum EstadoPiezas {
	DISPONIBLE,
	BLOQUEADA,
	VENDIDA,
	SUBASTA, 
	EXHIBIDA,
	DEVOLUCION
}

