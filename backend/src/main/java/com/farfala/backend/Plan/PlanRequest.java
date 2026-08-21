package com.farfala.backend.Plan;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {

    private Integer userId;

    private Integer planCatalogoId;

}