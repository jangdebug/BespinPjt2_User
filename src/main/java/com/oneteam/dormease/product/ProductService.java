package com.oneteam.dormease.product;

import com.oneteam.dormease.utils.pagination.Criteria;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ProductService {

    private final IProductMapper productMapper;
    public ProductService(IProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    //전체 상품 조회(ajax) (학생 소속 학교 상품)
    public Map<String, Object> mySchoolProduct(String zipCode) {
        log.info("selectAllProduct()");

        Map<String, Object> item = new HashMap<>();

        List<MySchoolProductDto> productDtos = productMapper.mySchoolProduct(zipCode);
        item.put("productDtos", productDtos);

        return item;
    }

    //상품 등록 확인
    public int registProductConfirm(ProductOrderDto productOrder, List<String> name, List<Integer> price, List<Integer> count, List<String> img) {
        log.info("registProductConfirm()");

        List<ProductOrderDto> productOrders = new ArrayList<>();

        for(int i=0; i<name.size(); i++){

            ProductOrderDto poDto = new ProductOrderDto();
            poDto.setId(productOrder.getId());
            poDto.setAllsum(productOrder.getSum());
            poDto.setName(name.get(i));
            poDto.setPrice(price.get(i)/count.get(i));
            poDto.setCount(count.get(i));
            poDto.setSum(price.get(i));
            poDto.setImg(img.get(i));

            productOrders.add(poDto);
        }

        return productMapper.registProductConfirm(productOrders);
    }

    //상품 검색 (ajax)
    public Map<String, Object> findProduct(String productName, String zipCode) {
        log.info("findProduct()");

        Map<String, Object> item = new HashMap<>();
        List<MySchoolProductDto> mySchoolProductDtos = new ArrayList<>();


        if(productName == "" || productName == null){       //전체 조회
            mySchoolProductDtos = productMapper.mySchoolProduct(zipCode);
        }
        else{
            mySchoolProductDtos = productMapper.findProduct(productName, zipCode);
        }

        item.put("mySchoolProductDtos", mySchoolProductDtos);

        return item;
    }

    //내역 확인
    public Map<String, Object> paymentHistory(String id, int pageNum, int amount) {
        log.info("paymentHistory()");

        Map<String, Object> list = new HashMap<>();

        Criteria criteria = new Criteria(pageNum, amount);
        ProductListDto productListDto = new ProductListDto(criteria.getSkip(), amount);
        productListDto.setId(id);

        List<ProductOrderDto> productOrderDtos = productMapper.paymentHistory(productListDto);
        int totalCnt = productMapper.dayCount(id);
        PageMakerDto pageMakerDto = new PageMakerDto(null, criteria, totalCnt);

        list.put("productOrderDtos", productOrderDtos);
        list.put("pageMakerDto", pageMakerDto);

        return list;
    }

    public Map<String, Object> detailHistory(String id, String regDate) {
        log.info("detailHistory()");

        Map<String, Object> item = new HashMap<>();
        List<ProductOrderDto> productOrderDtos = productMapper.detailHistory(id, regDate);

        item.put("productOrderDtos", productOrderDtos);

        return item;
    }
}
