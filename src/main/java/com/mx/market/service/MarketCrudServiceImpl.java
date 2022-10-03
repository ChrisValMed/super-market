package com.mx.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.mx.market.Utilerias.Utilerias;
import com.mx.market.dao.IMarketCrud;
import com.mx.market.exception.MarketException;
import com.mx.market.model.Producto;
import com.mx.market.model.UpdateCantidadRequest;

@Service
public class MarketCrudServiceImpl implements IMarketCrud,IMarketCrudService {
	
	static List<Producto> productos = new ArrayList<>();
	
	public MarketCrudServiceImpl() {
		
		Producto product = new Producto();
		product.setIdProduct(1);
		product.setNombre("Refresco");
		product.setDescripcion("Refresco de 3 litros");
		product.setPrecio(BigDecimal.TEN);
		productos.add(product);
		
		Producto product2 = new Producto();
		product2.setIdProduct(2);
		product2.setNombre("Jamon");
		product2.setDescripcion("Jamon tipo viena 1/2 Kg");
		product2.setPrecio(BigDecimal.TEN);
		productos.add(product2);
		
		Producto product3 = new Producto();
		product3.setIdProduct(3);
		product3.setNombre("Galleta");
		product3.setDescripcion("Galletas animalitos");
		product3.setPrecio(BigDecimal.ONE);
		productos.add(product3);
		
	}

	static Map<Integer, List<Integer>> carrito = new HashMap<>();
	
	@Override
	public List<Producto> getProductos() {
		return productos;
	}
	
	@Override
	public JsonObject getProductosJson() {
		JsonObject resultados = new JsonObject();
		resultados.put("productos",productos);
		return resultados;
	}

	@Override
	public List<Integer> addProductos(int idUser, int idProduct) {
		
		List<Integer> agregado = new ArrayList<>();
				
		if(getCarritoPorUser(idUser) != null) 
			agregado = getCarritoPorUser(idUser);
		
		
		if(Utilerias.existeProducto(productos,idProduct).isPresent()) {
			agregado.add(idProduct);
			addCarrito(idUser, agregado);
			SubProceso sub = new SubProceso();
			sub.start();
		}
		else
			return null;
		
		return agregado;
		
	}
	
	@Override
	public JsonObject addProductosJson(int idUser, int idProduct) {
		
		JsonObject resultados = new JsonObject();

		List<Integer> respuesta = addProductos(idUser,idProduct);
		
		if(respuesta == null)
			throw new MarketException("idProducto: "+ idProduct+" no existe en inventario");
		else 
			resultados.put("idProducto", respuesta);
		
		return resultados;
		
	}
	
	public void addCarrito(int idUser, List<Integer> idProductoList) {
		carrito.put(idUser, idProductoList);
	}
	
	
	public List<Integer> getCarritoPorUser(int idUser) {
		return carrito.get(idUser);
	}

	@Override
	public List<Integer> updateProductos(int idUser, int idProduct, int cantidad) {
		List<Integer> productos = new ArrayList<>();
		if(cantidad <= 0)
			return null;
		
		List<Integer> existeEnCarrito = getCarritoPorUser(idUser);
		
		if(existeEnCarrito == null || existeEnCarrito.isEmpty())
			throw new MarketException("No existen productos en el carrito del usuario");
		else {
			productos = existeEnCarrito;
			
			List<Integer> listaPorProducto = productos.stream()
	 				.filter(p -> p == idProduct)
	 				.collect(Collectors.toList());
			
			cantidad = (int) (cantidad - listaPorProducto.size());
			
			for(int i=0; i < cantidad; i++) {
				productos.add(idProduct);
			}
			
			addCarrito(idUser, productos);
			
		}
		
		return productos;
		
	}
	
	@Override
	public JsonObject updateProductosJson(UpdateCantidadRequest nuevaCantidad) {
		
		JsonObject resultados = new JsonObject();
		
		List<Integer> respuesta = updateProductos(nuevaCantidad.getIdUsuario(),
				nuevaCantidad.getIdProduct(), nuevaCantidad.getCantidadProducto());
		
		if(respuesta == null)
			throw new MarketException("cantidad debe ser mayor a cero");
		else 
			resultados.put("idProductos", respuesta);
		
		return resultados;
		
	}

	@Override
	public List<Producto> getProductos(int idUser) {
		
		List<Producto> encontrados =  new ArrayList<>();
		
		List<Integer> idProductosList = getCarritoPorUser(idUser);
	
		if(idProductosList != null) {
			
			idProductosList.stream()
			.forEach(idProd ->{
				Optional<Producto> existe = Utilerias.existeProducto(productos,idProd); 
				if(existe.isPresent()) {
					encontrados.add(existe.get());
				}
			});
			
		}
		
		return encontrados;
	}
	
	@Override
	public JsonObject getProductosJson(int idUser) {
		
		JsonObject resultados = new JsonObject();
		
		resultados.put("productos", getProductos(idUser));
		
		return resultados;
	}

	@Override
	public List<Integer> deleteProductos(int idUser,int idProduct) {
		
		List<Integer> listaActual = new ArrayList<>();
		
		List<Integer> idProductosList = getCarritoPorUser(idUser);
		
		if(idProductosList != null) {
			
			listaActual = idProductosList.stream()
						.filter(p -> p != idProduct)
						.collect(Collectors.toList());
			 
				addCarrito(idUser, listaActual);
			
		}
		
		return listaActual;
	}
	
	@Override
	public JsonObject deleteProductosJson(int idUser,int idProduct) {
		
		JsonObject resultados = new JsonObject();
		
		resultados.put("productos", deleteProductos(idUser,idProduct));
		
		return resultados;
	}

}
