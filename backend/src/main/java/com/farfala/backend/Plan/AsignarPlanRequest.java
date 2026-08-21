package com.farfala.backend.Plan;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignarPlanRequest {

    private Long userId;

    private Long planCatalogoId;

}