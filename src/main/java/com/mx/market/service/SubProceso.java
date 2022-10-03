package com.mx.market.service;

import com.mx.market.dao.IMensaje;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubProceso extends Thread implements IMensaje {
	
	public static final String MENSAJE_SAVE = "El producto ha sido agregado exitosamente.";

	@Override
	public void run() {
		
		Thread thread = new Thread();
		long time = 8000L;
		try {
			thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.start();
		
		guardaMensaje(MENSAJE_SAVE);
		
	}

	@Override
	public void guardaMensaje(String mensaje) {
		log.info("Registrando... {}", mensaje);
	}
	
	
	

}
