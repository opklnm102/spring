package me.dong.controller.api;

import me.dong.model.domain.Order;
import me.dong.model.service.OrderService;
import me.dong.model.vo.*;
import me.dong.security.SecurityUtils;
import me.dong.util.ParameterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 주문 목록 조회
     *
     * @param requestVO
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public DataListResponseVO<Order> list(DataListRequestVO requestVO) {
        Long userId = SecurityUtils.getCurrentUser().getId();
        Page<Order> page = orderService.getOrders(userId, requestVO.getPageable());
        return new DataListResponseVO<Order>(page);
    }

    /**
     * 주문 상세 조회
     * @param id Order id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DataResponseVO<Order> detail(@PathVariable Long id) {
        Order order = orderService.findOne(id);
        return new DataResponseVO<Order>(order);
    }

    /**
     * 상품 주문
     * @param requestVO 주문정보가 담긴 모델객체
     * @return
     */
    // RequestBody 샘플
    @RequestMapping(method = RequestMethod.POST)
    public ResponseVO order(@RequestBody OrderRequestVO requestVO) {
        ParameterUtil.checkParameterEmpty(requestVO.getRecipientName(), requestVO.getPayMethod(), requestVO.getDeliveryAddress());
        orderService.order(SecurityUtils.getCurrentUser(), requestVO);
        return ResponseVO.ok();
    }

    /**
     * 주문 취소
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseVO orderCancel(@PathVariable Long id){
        orderService.cancel(SecurityUtils.getCurrentUser(), id);
        return ResponseVO.ok();
    }
}
