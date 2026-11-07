package com.reservas.pagos.domain.port.out;

import com.reservas.pagos.domain.model.ResultadoCobro;

import java.math.BigDecimal;

public interface PasarelaPagoPort {

    ResultadoCobro cobrar(BigDecimal monto);

}
