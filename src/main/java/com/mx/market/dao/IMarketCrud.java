package com.mx.market.dao;

import java.util.List;

import com.mx.market.model.Producto;

public interface IMarketCrud {
	
	List<Producto> getProductos();
	
	List<Integer> addProductos(int idUser, int idProduct);
	
	List<Integer> updateProductos(int idUser, int idProduct, int cantidad);
	
	List<Producto> getProductos(int idUser);
	
	List<Integer> deleteProductos(int idUser, int idProduct);

}
