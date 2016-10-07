package me.dong.admin;

import me.dong.model.domain.Order;
import me.dong.model.domain.OrderStatus;
import me.dong.model.service.OrderService;
import me.dong.model.vo.ResponseVO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller(value = "adminOrderController")
@RequestMapping("/adimn/order")
@SessionAttributes(value = "order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Page<Order> page = orderService.getOrders(pageable);
        model.addAttribute("page", page);
        return "order/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.DELETE)
    public String cancelList(Model model, Pageable pageable){
        Page<Order> page = orderService.getOrdersByOrderStatus(OrderStatus.CANCELED, pageable);
        model.addAttribute("page", page);
        return "order/list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam Long id, Model model){
        Order order = orderService.findOne(id);
        model.addAttribute("order", order);
        model.addAttribute("orderStatuses", OrderStatus.values());
        return "order/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO save(@ModelAttribute Order order, SessionStatus sessionStatus){
        orderService.save(order);
        sessionStatus.setComplete();
        return ResponseVO.ok();
    }
}
