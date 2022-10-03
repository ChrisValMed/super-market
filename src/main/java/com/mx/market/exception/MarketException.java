package com.mx.market.exception;

public class MarketException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String MENSAJE_DEFAULT="Elemento no encontrado";
	
	public MarketException(String mensaje) {
	  super(mensaje);
	}

}
