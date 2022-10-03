package com.mx.market.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.mx.market.model.UpdateCantidadRequest;

public interface IMarketCrudService {
	
	JsonObject getProductosJson();
	
	JsonObject addProductosJson(int idUser, int idProduct);
	
	JsonObject updateProductosJson(UpdateCantidadRequest nuevaCantidad);
	
	JsonObject getProductosJson(int idUser);
	
	JsonObject deleteProductosJson(int idUser, int idProduct);

}
