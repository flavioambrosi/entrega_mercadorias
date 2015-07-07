package org.logistica.servicos;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.NamingException;

import org.logistica.MenorCaminho;
import org.logistica.ServicosEntregaMercadorias;
import org.logistica.exception.MapaCadastradoException;
import org.logistica.exception.VerticeNotFoundExcetion;

@WebService
public class ServicoCadastraMapa {

    @EJB
    ServicosEntregaMercadorias servicos;

    @WebMethod
    public MensagemRetorno cadastraMapa(@WebParam(name = "origem") String origem, @WebParam(name = "destino") String destino,
            @WebParam(name = "distancia") Integer distancia) {

        MensagemRetorno msgRetorno = new MensagemRetorno();
        try {
            if (this.servicos == null) {
                this.lookup();
            }
            this.servicos.adicionaMapa(origem, destino, distancia);

            msgRetorno.setResulMessage("Rota cadastrada com sucesso");
        } catch (MapaCadastradoException e) {
            msgRetorno.setResulMessage("Erro no cadastro da rota. Consulte Log no servidor");
            e.printStackTrace();
        }

        return msgRetorno;
    }

    @WebMethod
    public MensagemRetorno buscaCaminho(@WebParam(name = "origem") String origem, @WebParam(name = "destino") String destino,
            @WebParam(name = "autonomia") BigDecimal autonomia,
            @WebParam(name = "valorCombustivel") BigDecimal valorCombustivel) {
        MensagemRetorno msgRetorno = new MensagemRetorno();
        try {
            if (servicos == null) {
                this.lookup();
            }
            MenorCaminho resultado = servicos.buscaCaminho(origem, destino, autonomia, valorCombustivel);

            if(resultado.getMensagemErro() != null){
                msgRetorno.setResulMessage(resultado.getMensagemErro());
            } else {
                msgRetorno
                    .setResulMessage("A rota a ser utilizada custara: " + resultado.getMenorCaminho() + ". Custo: "
                            + resultado.getCustoRota() + ". Distancia total: " + resultado.getDistanciaRota());
            }
        } catch (VerticeNotFoundExcetion e) {
            e.printStackTrace();
            msgRetorno.setResulMessage("Erro no cadastro da rota. Consulte Log no servidor");
        }

        return msgRetorno;
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
