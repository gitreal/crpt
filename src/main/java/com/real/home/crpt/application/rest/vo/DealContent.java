package com.real.home.crpt.application.rest.vo;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Validated
public class DealContent {

    @NotNull
    @Size(min = 9, max = 9)
    private String seller;

    @NotNull
    @Size(min = 9, max = 9)
    private String customer;

    @NotNull
    private List<ProductInfo> products;

}
