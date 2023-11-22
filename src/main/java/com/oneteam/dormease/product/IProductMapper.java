package com.oneteam.dormease.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductMapper {

    public List<MySchoolProductDto> mySchoolProduct(String zipCode);
    public int registProductConfirm(List<ProductOrderDto> productOrders);
    public List<MySchoolProductDto> findProduct(String productName, String zipCode);
    public List<ProductOrderDto> paymentHistory(ProductListDto productListDto);
    public int dayCount(String id);
    public List<ProductOrderDto> detailHistory(String id, String regDate);
}
