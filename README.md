Problema proposto:

Desenvolver o novo sistema de entregas visando sempre o menor custo. Para popular sua base de dados o sistema precisa 
expor um Webservices que aceite o formato de malha logística (exemplo abaixo), nesta mesma requisição o requisitante 
deverá informar um nome para este mapa. É importante que os mapas sejam persistidos para evitar que a cada novo 
deploy todas as informações desapareçam. O formato de malha logística é bastante simples, cada linha mostra uma rota: 
ponto de origem, ponto de destino e distância entre os pontos em quilômetros.

AB 10  
BD 15  
AC 20  
CD 30  
BE 50  
DE 30  

Com os mapas carregados o requisitante irá procurar o menor valor de entrega e seu caminho, para isso ele passará o 
nome do ponto de origem, nome do ponto de destino, autonomia do caminhão (km/l) e o valor do litro do combustível, 
agora sua tarefa é criar este Webservices. Um exemplo de entrada seria, origem A, destino D, autonomia 10, valor
do litro 2,50; a resposta seria a rota A B D com custo de 6,25.

De inicio identifiquei que se trata de um problema de menor caminho e para soluciona-lo estou utilizando a API 
JUNG (http://jung.sourceforge.net/). Desenvolvi a camada de persistencia utilizando JPA + Hibernate. Desenvolvi uma camada de serviços, utilizando EJB3, que será responsável pelo cadastramento do mapa, pela montagem do grafo e pela busca da rota desejada. O códido se encontra em entrega-mercadorias-ejb.

Foi desenvolvido também os WebServices ServicoBuscaMenorCaminho e ServicoCadastraMapa para realizar as operações citadas. O códido se encontra em entrega-mercadorias-web.

O projeto entrega-mercadorias-ear é responsável pelo empacotamento dos componentes ejb e web em um arquivo ear.

Utilizado banco MySql, servidor de aplicações JBoss AS 7 e maven para buildar a aplicação.

Para construir a aplicaçãodeve-se ter o maven instalado na máquina. Executar mvn clean install.

Realizar as seguintes configurações no JBOSS:
1 - Criar o modulo para configurar o MySql. Copiar os arquivos da pasta conf-jboss\modules\com\mysql\main em
<JBOSS_HOME>\modules\com\mysql\main
2 - Configurar o aplicaçãoDS no arquivo <JBOSS_HOME>\standalone\configuration\standalone-preview.xml conforme arquivo
em conf-jboss\standalone\configurations\standalone-preview.xml

Para a criação do banco, executar o script cria_banco_mysql.sql

Pendente da elaboração de cenários de testes.
