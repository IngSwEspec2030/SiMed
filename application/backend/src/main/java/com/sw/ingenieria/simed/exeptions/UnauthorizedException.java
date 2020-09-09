package com.sw.ingenieria.simed.exeptions;

public class UnauthorizedException extends RuntimeException {
    public static final String DESCRIPCION = "No autorizado";

    public UnauthorizedException(){
        super(DESCRIPCION);
    }

    public UnauthorizedException(String detalleError){
        super(DESCRIPCION+" : "+detalleError);
    }
}
