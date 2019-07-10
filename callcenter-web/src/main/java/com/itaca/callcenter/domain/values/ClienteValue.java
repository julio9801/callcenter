package com.itaca.callcenter.domain.values;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteValue implements Serializable {
	private static final long serialVersionUID = -3738116843318186991L;
	private Long id;
        private int grupo;
        private int noCliente;
	
	private String nombre;
        private String appaterno;
        private String apmaterno;
        

	public ClienteValue() {

	}

    public ClienteValue(int noCliente,int grupo, String nombre, String appaterno, String apmaterno) {
        this.grupo = grupo;
        this.noCliente = noCliente;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
    }

	

	
}
