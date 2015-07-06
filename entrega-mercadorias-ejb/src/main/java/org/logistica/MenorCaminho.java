/**
 *
 */
package org.logistica;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author (ambrosi@cpqd.com.br)
 *
 */
public class MenorCaminho implements Serializable {

    String menorCaminho;

    Number distanciaRota;

    BigDecimal custoRota;


    /**
     * Obtém o valor do atributo menorCaminho. Verifique o comentário do atributo para mais detalhes.
     * @return O menorCaminho.
     */
    public String getMenorCaminho() {
        return this.menorCaminho;
    }


    /**
     * Define o valor do atributo menorCaminho. Verifique o comentário do atributo para mais detalhes.
     * @param menorCaminho O menorCaminho a ser definido.
     */
    public void setMenorCaminho(String menorCaminho) {
        this.menorCaminho = menorCaminho;
    }


    /**
     * Obtém o valor do atributo distanciaRota. Verifique o comentário do atributo para mais detalhes.
     * @return O distanciaRota.
     */
    public Number getDistanciaRota() {
        return this.distanciaRota;
    }


    /**
     * Define o valor do atributo distanciaRota. Verifique o comentário do atributo para mais detalhes.
     * @param distanciaRota O distanciaRota a ser definido.
     */
    public void setDistanciaRota(Number distanciaRota) {
        this.distanciaRota = distanciaRota;
    }



    /**
     * Obtém o valor do atributo custoRota. Verifique o comentário do atributo para mais detalhes.
     * @return O custoRota.
     */
    public BigDecimal getCustoRota() {
        return this.custoRota;
    }



    /**
     * Define o valor do atributo custoRota. Verifique o comentário do atributo para mais detalhes.
     * @param custoRota O custoRota a ser definido.
     */
    public void setCustoRota(BigDecimal custoRota) {
        this.custoRota = custoRota;
    }


}
