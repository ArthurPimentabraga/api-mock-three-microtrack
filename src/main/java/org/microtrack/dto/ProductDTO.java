package org.microtrack.dto;

import lombok.*;
import org.microtrack.dto.enums.StatusProductEnum;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String id;
    private String nomeProduto;
    private String descricaoProduto;
    private StatusProductEnum status;
    private Double totalPayment;

    public String getString() {
        return String.format(
                "{\"id\": \"%s\", \"nomeProduto\": \"%s\", \"descricaoProduto\": \"%s\", " +
                        "\"status\": \"%s\", \"totalPayment\": \"%s\"}",
                id, nomeProduto, descricaoProduto, status, totalPayment
        );
    }

}