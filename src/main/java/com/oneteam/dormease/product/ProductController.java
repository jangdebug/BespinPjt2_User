package com.oneteam.dormease.product;

import com.oneteam.dormease.user.school.SchoolDto;
import com.oneteam.dormease.user.student.StudentDto;
import com.oneteam.dormease.utils.pagination.PageDefine;
import com.oneteam.dormease.utils.pagination.PageMakerDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/product")
@Tag(name = "Product", description = "매점 픽업 API")
public class ProductController {

    //생성자 주입
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    /*
     * 우리 학교 상품 보기
     */
    @GetMapping("/mySchoolProductForm")
    public String mySchoolProductForm(){
        log.info("mySchoolProductForm()");

        String nextPage = "product/mySchoolProductForm";

        return nextPage;
    }

    /*
     * 전체 상품 조회(ajax)
     */
    @PostMapping("/mySchoolProduct")
    @ResponseBody
    public Object mySchoolProduct(@RequestBody Map<String ,String> msgMap){
        log.info("mySchoolProduct()");

        String zipCode = msgMap.get("zipCode");

        Map<String, Object> resultMap = productService.mySchoolProduct(zipCode);

        return resultMap;
    }

    /*
     * 상품 검색 (ajax)
     */
    @PostMapping("/findProduct")
    @ResponseBody
    public Object selectProduct(@RequestBody Map<String ,String> msgMap){
        log.info("selectProduct()");

        String productName = msgMap.get("name");
        String zipCode = msgMap.get("zipCode");

        Map<String, Object> resultMap = productService.findProduct(productName, zipCode);

        return resultMap;
    }

    /*
     * 상품 등록 확인
     */
    @PostMapping("/registProductConfirm")
    public String registProductConfirm(ProductOrderDto productOrder,
                                       @RequestParam("name") List<String> name,
                                       @RequestParam("price") List<Integer> price,
                                       @RequestParam("cnt") List<Integer> cnt,
                                       @RequestParam("img") List<String> img){
        log.info("registProductConfirm()");

        int result = productService.registProductConfirm(productOrder, name, price, cnt, img);

        return "redirect:/product/paymentHistory";
    }

    /*
     * 내역 확인
     */
    @GetMapping("/paymentHistory")
    public String paymentHistory(HttpSession session,
                                 Model model,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_PAGE_NUMBER) int pageNum,
                                 @RequestParam(value = "amount", required = false, defaultValue = PageDefine.DEFAULT_PRODUCT_AMOUNT) int amount) {
        log.info("paymentHistory()");

        String nextPage = "product/paymentHistory";

        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");

        //페이지와 DTO 동시 관리
        Map<String, Object> listPage = productService.paymentHistory(loginedStudentDto.getId(), pageNum, amount);

        List<ProductOrderDto> productOrderDtos = (List<ProductOrderDto>) listPage.get("productOrderDtos");
        PageMakerDto pageMakerDto = (PageMakerDto) listPage.get("pageMakerDto");

        model.addAttribute("productOrderDtos", productOrderDtos);
        model.addAttribute("pageMakerDto", pageMakerDto);

        return nextPage;
    }

    /*
     * 상세 내역 (ajax)
     */
    @PostMapping("/detailHistory")
    @ResponseBody
    public Object detailHistory(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("detailHistory()");

        String reg_date = msgMap.get("reg_date");

        StudentDto loginedStudentDto = (StudentDto) session.getAttribute("loginedStudentDto");

        Map<String, Object> resultMap = productService.detailHistory(loginedStudentDto.getId(), reg_date);

        return resultMap;
    }





}
