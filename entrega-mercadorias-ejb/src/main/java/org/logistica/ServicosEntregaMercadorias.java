/**
 *
 */
package org.logistica;

import java.math.BigDecimal;

import javax.ejb.Local;

import org.logistica.exception.MapaCadastradoException;
import org.logistica.exception.VerticeNotFoundExcetion;


/**
 * Interface de servicos.
 *
 */
@Local
public interface ServicosEntregaMercadorias {

    /**
     * Adiciona pontos no mapa
     * @param origem ponto de origem
     * @param destino ponto de destino
     * @param distancia distancia entre os pontos
     * @throws MapaCadastradoException caso ocorra algum erro no processamento
     */
    public void adicionaMapa(String origem, String destino, Integer distancia) throws MapaCadastradoException;

    /**
     * Retorna o valor da viagem pelo menor caminho entre dois pontos.
     * @param origem ponto de origem
     * @param destino ponto de destino
     * @param autonomia litros/km do caminhao
     * @param valorCombustivel valor do combustivel
     * @return custo da viagem entre os pontos
     * @throws VerticeNotFoundExcetion caso ocorra algum erro no processamento
     */
    public BigDecimal buscaCaminho(String origem, String destino, BigDecimal autonomia, BigDecimal valorCombustivel) throws VerticeNotFoundExcetion;
}
