package com.mx.market.model;

import java.math.BigDecimal;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Generated
@Getter
@Setter
@ToString
public class Producto {
	
	private int idProduct;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;

}
