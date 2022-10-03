package com.mx.market.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mx.market.model.Producto;
import com.mx.market.model.UpdateCantidadRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarketCrudServiceImplTest {

	@Autowired
	MarketCrudServiceImpl crud;
	
	
	@Test
	public void addProductos() {
		
		int idUser = 1;
		int idproduct = 1;
		
		Object productos = crud.addProductosJson(idUser, idproduct).get("idProducto");
		
		List<Integer> resp = (List<Integer>) productos;
		
		assertNotNull(resp);
		
		Optional<Integer> encontrado = resp.stream()
				.filter(p -> p == 1)
				.findFirst();
		
		assertTrue(encontrado.isPresent());
		
		
	}
	
	
	@Test
	public void getProductos() {
		
		Object productos = crud.getProductosJson().get("productos");
	
	    List<Producto> prods = (List<Producto>) productos;
		
		assertTrue(!prods.isEmpty());
		
		Optional<Producto> encontrado = prods.stream()
								.filter(p -> p.getIdProduct() == 1)
								.findFirst();
		
		assertTrue(encontrado.isPresent());
		
		for(Producto p:prods)
			System.out.println(p);
			
		
		
	}
	
	
	@Test
	public void upProductos() {
		
		
		List<Integer> productos =  new ArrayList<>();
		
		productos.add(1);
		
		crud.addCarrito(1, productos );
	
	
		int idUser = 1;
		int idProduct = 1;
		int cantidad = 3;
		
		UpdateCantidadRequest nuevaCantidad =  new UpdateCantidadRequest();
		
		nuevaCantidad.setIdUsuario(idUser);
		nuevaCantidad.setIdProduct(idProduct);
		nuevaCantidad.setCantidadProducto(cantidad);
		
		Object productosResp = crud.updateProductosJson(nuevaCantidad ).get("idProductos");
		
		List<Integer> resp = (List<Integer>) productosResp;
		
		assertTrue(!resp.isEmpty());
		
		long total = resp.stream()
								.filter(p -> p == 1)
								.count();
		
		assertEquals(3L, total);
		
		for(Integer p:resp)
			System.out.println(p);
			
		
		
	}
	
	
	@Test
	public void getProductosPorUser() {
		
		List<Integer> productos =  new ArrayList<>();
		
		productos.add(1);
		productos.add(2);
		productos.add(2);
		
		crud.addCarrito(1, productos );
		
		Object prodList = crud.getProductosJson(1).get("productos");
	
	    List<Producto> prods = (List<Producto>) prodList;
		
		assertTrue(!prods.isEmpty());
		
		Optional<Producto> encontrado = prods.stream()
								.filter(p -> p.getIdProduct() == 1)
								.findFirst();
		
		assertTrue(encontrado.isPresent());
		
		for(Producto p:prods)
			System.out.println(p);
			
		
		
	}
	
	
	@Test
	public void deleteProductosPorUser() {
		
		List<Integer> productos =  new ArrayList<>();
		
		productos.add(1);
		productos.add(2);
		
		crud.addCarrito(1, productos );
	
		Object listProd = crud.deleteProductosJson(1,1).get("productos");
	
	    List<Integer> resp = (List<Integer>) listProd;
		
		assertTrue(!resp.isEmpty());
		
		Optional<Integer> prod2 = resp.stream()
								.filter(p -> p == 2)
								.findFirst();
		
		assertTrue(prod2.isPresent());
		
		
		
		Optional<Integer> prod1 = resp.stream()
				.filter(p -> p == 1)
				.findFirst();

		assertFalse(prod1.isPresent());
	
		
	}
	
	

}
