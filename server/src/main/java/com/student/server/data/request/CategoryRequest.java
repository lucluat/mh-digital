package com.student.server.data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest extends PageableRequest {

    private String id;

    @NotBlank(message = "Category name must be not blank")
    private String name;
}
