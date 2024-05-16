package org.example.barappapi.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.barappapi.enums.order.Status;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private Status status;
}
