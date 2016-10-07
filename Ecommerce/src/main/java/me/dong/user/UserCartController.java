package me.dong.user;

import me.dong.model.domain.CartProduct;
import me.dong.model.service.CartService;
import me.dong.model.vo.ResponseVO;
import me.dong.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("userCartController")
@RequestMapping(value = "/user/cart")
public class UserCartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addProduct(@RequestParam(value = "id") Long productId, @RequestParam(value = "bcnt") Integer buyCount){
        System.out.println("## 카트에 담앗다 : " + productId + " , buyCount : " + buyCount);
        Long userId = SecurityUtils.getCurrentUser().getId();
        cartService.addProduct(userId, productId, buyCount);
        return ResponseVO.ok();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Long userId = SecurityUtils.getCurrentUser().getId();
        Page<CartProduct> page = cartService.getCartProducts(userId, pageable);
        model.addAttribute("page", page);
        return "user/product/cartlist";
    }
}
