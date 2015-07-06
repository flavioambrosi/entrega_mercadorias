package org.logistica.servicos;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.logistica.ServicosEntregaMercadorias;
import org.logistica.exception.VerticeNotFoundExcetion;

@WebService
public class ServicoBuscaMenorCaminho {

	@EJB
	ServicosEntregaMercadorias servicos;

	@WebMethod
	public void buscaCaminho(String origem, String destino, Integer distancia, BigDecimal autonomia, BigDecimal valorCombustivel) {

			try {
				servicos.buscaCaminho(origem, destino, autonomia, valorCombustivel);
			} catch (VerticeNotFoundExcetion e) {
				e.printStackTrace();
			}

	}
}
