/**
 *
 */
package org.logistica.servicos;

import java.io.Serializable;


/**
 * Mensagem de retorno de webservices
 * @author (ambrosi@cpqd.com.br)
 *
 */
public class MensagemRetorno implements Serializable {

    private String resulMessage;


    /**
     * Obtém o valor do atributo resulMessage. Verifique o comentário do atributo para mais detalhes.
     * @return O resulMessage.
     */
    public String getResulMessage() {
        return this.resulMessage;
    }


    /**
     * Define o valor do atributo resulMessage. Verifique o comentário do atributo para mais detalhes.
     * @param resulMessage O resulMessage a ser definido.
     */
    public void setResulMessage(String resulMessage) {
        this.resulMessage = resulMessage;
    }



}
