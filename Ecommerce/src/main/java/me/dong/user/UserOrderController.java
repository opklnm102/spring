package me.dong.user;

import com.sun.org.apache.bcel.internal.generic.POP;
import me.dong.model.domain.OrderProduct;
import me.dong.model.service.OrderService;
import me.dong.model.vo.OrderRequestVO;
import me.dong.model.vo.ResponseVO;
import me.dong.security.SecurityUtils;
import me.dong.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("userOrderController")
@RequestMapping(value = "/user/order")
public class UserOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO addOrder(@RequestBody OrderRequestVO requestVO){
        ParameterUtil.checkParameterEmpty(requestVO.getRecipientName(), requestVO.getPayMethod(), requestVO.getDeliveryAddress());
        orderService.order(SecurityUtils.getCurrentUser(), requestVO);
        return ResponseVO.ok();
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Long userId = SecurityUtils.getCurrentUser().getId();
        Page<OrderProduct> page = orderService.getOrderProducts(userId, pageable);
        model.addAttribute("page", page);
        return "/user/product/orderlist";
    }
}
