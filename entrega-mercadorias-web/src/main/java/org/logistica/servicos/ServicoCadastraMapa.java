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
	public void cadastraMapa(@WebParam(name = "origem") String origem, @WebParam(name = "destino") String destino, @WebParam(name = "distancia")Integer distancia){

		try {
			servicos.adicionaMapa(origem, destino, distancia);
		} catch (MapaCadastradoException e) {
			e.printStackTrace();
		}
	}

	@WebMethod
	public void buscaCaminho(@WebParam(name = "origem") String origem,
			@WebParam(name = "destino") String destino,
			@WebParam(name = "distancia")Integer distancia,
			@WebParam(name = "autonomia") BigDecimal autonomia,
			@WebParam(name = "valorCombustivel") BigDecimal valorCombustivel) {

			try {
				servicos.buscaCaminho(origem, destino, autonomia, valorCombustivel);
			} catch (VerticeNotFoundExcetion e) {
				e.printStackTrace();
			}

	}

}
