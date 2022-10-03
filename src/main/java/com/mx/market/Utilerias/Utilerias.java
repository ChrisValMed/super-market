package com.mx.market.Utilerias;

import java.util.List;
import java.util.Optional;

import com.mx.market.model.Producto;

public class Utilerias {
	
	public static Optional<Producto> existeProducto(List<Producto> productos,int idProducto) {
		return productos.stream()
		.filter(p -> p.getIdProduct() == idProducto).findFirst();		
}

}
