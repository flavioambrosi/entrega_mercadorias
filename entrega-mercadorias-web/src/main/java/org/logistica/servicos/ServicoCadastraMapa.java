package org.logistica.servicos;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.logistica.ServicosEntregaMercadorias;
import org.logistica.exception.MapaCadastradoException;
import org.logistica.exception.VerticeNotFoundExcetion;

@WebService
public class ServicoCadastraMapa {

	@EJB
	ServicosEntregaMercadorias servicos;

	@WebMethod
	public void cadastraMapa(String origem, String destino, Integer distancia){

		try {
			servicos.adicionaMapa(origem, destino, distancia);
		} catch (MapaCadastradoException e) {
			e.printStackTrace();
		}
	}

	@WebMethod
    public void buscaCaminho(String origem, String destino, Integer distancia, BigDecimal autonomia, BigDecimal valorCombustivel) {

			try {
				servicos.buscaCaminho(origem, destino, autonomia, valorCombustivel);
			} catch (VerticeNotFoundExcetion e) {
				e.printStackTrace();
			}

	}

}
