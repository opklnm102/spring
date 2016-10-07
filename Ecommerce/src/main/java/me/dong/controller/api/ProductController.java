package me.dong.controller.api;

import me.dong.model.domain.Product;
import me.dong.model.service.ProductService;
import me.dong.model.vo.DataListRequestVO;
import me.dong.model.vo.DataListResponseVO;
import me.dong.model.vo.DataResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * 상품 목록 조회
     * @param requestVO 상품 목록 page 정보
     * @return 상품 목록
     */
    @RequestMapping(method = RequestMethod.GET)
    public DataListResponseVO<Product> list(DataListRequestVO requestVO){
        Page<Product> page = productService.findProducts(requestVO.getPageable());
        return new DataListResponseVO<Product>(page);
    }

    /**
     * 상품 상세 조회
     * @param id 상세조회할 상품 id
     * @return 조회한 상품 정보
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private DataResponseVO<Product> detail(@PathVariable Long id){
        Product product = productService.findOne(id);
        return new DataResponseVO<Product>(product);
    }



}
