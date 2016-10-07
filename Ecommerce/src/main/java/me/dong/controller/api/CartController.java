package me.dong.controller.api;


import me.dong.model.domain.CartProduct;
import me.dong.model.service.CartService;
import me.dong.model.vo.DataListRequestVO;
import me.dong.model.vo.DataListResponseVO;
import me.dong.model.vo.ResponseVO;
import me.dong.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;

    /**
     * 장바구니 조회
     * @param requestVO 페이지 정보
     * @return 장바구니 목록
     */
    @RequestMapping(method = RequestMethod.GET)
    public DataListResponseVO<CartProduct> list(DataListRequestVO requestVO){
        Long userId = SecurityUtils.getCurrentUser().getId();
        Page<CartProduct> page = cartService.getCartProducts(userId, requestVO.getPageable());
        return new DataListResponseVO<CartProduct>(page);
    }

    /**
     * 장바구니 담기
     * @param productId 장바구니에 추가할 상품 id
     * @param buyCount 상품이 팔린 갯수
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO addProduct(@RequestParam(value = "id") Long productId, @RequestParam Integer buyCount){
        Long userId = SecurityUtils.getCurrentUser().getId();
        cartService.addProduct(userId, productId, buyCount);
        return ResponseVO.ok();
    }

    /**
     * 장바구니 상품 삭제
     * @param id 삭제할 상품 id
     * @return 응답 객체
     */
    @RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
    public ResponseVO deleteProduct(@PathVariable Long id){
        Long userId = SecurityUtils.getCurrentUser().getId();
        cartService.deleteProduct(userId, id);
        return ResponseVO.ok();
    }
}
