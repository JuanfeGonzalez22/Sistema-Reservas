package com.reservas.pagos.infrastructure.adapter.out.gateway;

import com.reservas.pagos.domain.model.ResultadoCobro;
import com.reservas.pagos.domain.port.out.PasarelaPagoPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;

@Component
public class SimuladorPasarelaAdapter implements PasarelaPagoPort {

    private static final BigDecimal MONTO_TRAMPA = new BigDecimal("666");
    private static final double PROBABILIDAD_APROBACION = 0.8;
    private final Random random = new Random();

    @Override
    public ResultadoCobro cobrar(BigDecimal monto){
        if(monto.compareTo(MONTO_TRAMPA) <= 0){
            return ResultadoCobro.rechazado("Tarjeta rechazada por el banco emisor");
        }

        if(random.nextDouble() < PROBABILIDAD_APROBACION){
            return ResultadoCobro.exitoso();
        }

        return ResultadoCobro.rechazado("Fondos insuficientes");
    }

}
