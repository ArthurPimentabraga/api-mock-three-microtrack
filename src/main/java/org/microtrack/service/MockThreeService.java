package org.microtrack.service;

import lombok.AllArgsConstructor;
import org.microtrack.dto.ProductDTO;
import org.microtrack.dto.ResponseTrace;
import org.microtrack.dto.Trace;
import org.microtrack.dto.enums.StatusProductEnum;

import java.io.IOException;
import java.sql.Timestamp;

@AllArgsConstructor
public class MockThreeService {

    public void updateStatusPayment(ProductDTO body) throws IOException, InterruptedException {
        // TODO Criar Beans para cada objeto singleton
        Manager manager = new Manager(true);
        TraceService traceService = new TraceService();

        ProductDTO product = mockUpdateStatus(body);
        registerTrace(body, product, traceService, manager, "status-atualizado-pagamento-processado");
        Thread.sleep(5000);
        registerTrace(body, product, traceService, manager, "pagamento-registrado-banco-dados");
    }

    public ProductDTO mockUpdateStatus(ProductDTO product) {
        product.setStatus(StatusProductEnum.PAYMENT_PROCESSED);
        return product;
    }

    private static void registerTrace(
            ProductDTO body, ProductDTO product, TraceService traceService, Manager manager, String checkpointName
    ) throws IOException, InterruptedException {
        Trace trace = Trace.builder()
                .traceId(body.getId())
                .serviceName("api-mock-two-microtrack")
                .timestamp(new Timestamp(System.currentTimeMillis())) // TODO SDK deveria registrar
                .checkpointName(checkpointName)
                .isError(Boolean.FALSE)
                .genericData(product)
                .build();

        ResponseTrace response = traceService.checkpoint(manager, trace);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());
    }

    private static void registerErrorTrace(ProductDTO body, ProductDTO product, TraceService traceService, Manager manager) throws IOException, InterruptedException {
        Trace trace = Trace.builder()
                .traceId(body.getId())
                .serviceName("api-mock-three-microtrack")
                .timestamp(new Timestamp(System.currentTimeMillis())) // TODO SDK deveria registrar
                .checkpointName("error-calculo-total-compra")
                .isError(Boolean.TRUE)
                .genericData(product)
                .build();

        ResponseTrace response = traceService.checkpoint(manager, trace);

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Message: " + response.getMessage());
    }

}
