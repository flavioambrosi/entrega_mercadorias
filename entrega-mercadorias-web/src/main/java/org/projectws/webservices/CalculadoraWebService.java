package org.projectws.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CalculadoraWebService {
    @WebMethod
    double calcular(@WebParam(name = "x") double x,
            @WebParam(name = "y") double y);
}
