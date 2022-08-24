package com.real.home.crpt.application.rest.vo;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class ProductInfo {

    @NotNull
    String name;

    @NotNull
    @Size(min = 13, max = 13)
    String code;

}
