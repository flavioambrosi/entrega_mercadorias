package org.logistica.servicos;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.NamingException;

import org.logistica.ServicosEntregaMercadorias;
import org.logistica.exception.MapaCadastradoException;
import org.logistica.exception.VerticeNotFoundExcetion;

@WebService
public class ServicoCadastraMapa {

    @EJB
    ServicosEntregaMercadorias servicos;

    @WebMethod
    public void cadastraMapa(@WebParam(name = "origem") String origem, @WebParam(name = "destino") String destino,
            @WebParam(name = "distancia") Integer distancia) {

        try {
            if (this.servicos == null) {
                this.lookup();
            }
            this.servicos.adicionaMapa(origem, destino, distancia);
        } catch (MapaCadastradoException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public void buscaCaminho(@WebParam(name = "origem") String origem, @WebParam(name = "destino") String destino,
            @WebParam(name = "autonomia") BigDecimal autonomia,
            @WebParam(name = "valorCombustivel") BigDecimal valorCombustivel) {

        try {
            if (servicos == null) {
                this.lookup();
            }
            servicos.buscaCaminho(origem, destino, autonomia, valorCombustivel);
        } catch (VerticeNotFoundExcetion e) {
            e.printStackTrace();
        }

    }

    private void lookup() {
        String jndi = "java:global/entrega-mercadorias/entrega-mercadorias-ejb-0.0.1-SNAPSHOT/ejb/ServicosEntregaMercadorias";
        try {
            this.servicos = (ServicosEntregaMercadorias) new javax.naming.InitialContext().lookup(jndi);
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
