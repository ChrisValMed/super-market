package com.mx.market.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.mx.market.model.UpdateCantidadRequest;
import com.mx.market.service.IMarketCrudService;


@RestController
@RequestMapping("/")
public class MarketController {
	
	@Autowired
	private IMarketCrudService market;
	
	@GetMapping(path="/productos",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonObject> getProductos() {
    	return ResponseEntity.status(HttpStatus.OK).body(market.getProductosJson());
	}
	
	@PostMapping(path="/add",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonObject> addProductos(
				@RequestHeader(value="x-id-usuario") int idUser,@RequestParam(value="idProduct") int idProduct) {
    	return ResponseEntity.status(HttpStatus.OK).body(market.addProductosJson(idUser, idProduct));
	}
	
	@PutMapping(path="/update",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonObject> updateProductos(@RequestBody UpdateCantidadRequest nuevaCantidad) {
	return ResponseEntity.status(HttpStatus.OK).body(market.updateProductosJson(nuevaCantidad));
	}
	
	@GetMapping(path="/productos/usuario/{idUsuario}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonObject> getProductos(@PathVariable("idUsuario") int idUsuario) {
    	return ResponseEntity.status(HttpStatus.OK).body(market.getProductosJson(idUsuario));
	}
	
	
	@DeleteMapping(path="/delete/{idProducto}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonObject> deleteProductos(@RequestHeader("x-id-usuario") int idUsuario,
			@PathVariable("idProducto") int idProducto) {
    	return ResponseEntity.status(HttpStatus.OK).body(market.deleteProductosJson(idUsuario, idProducto));
	}
	
	
}
