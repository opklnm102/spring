package me.dong.user;

import me.dong.model.domain.Product;
import me.dong.model.domain.User;
import me.dong.model.service.ProductService;
import me.dong.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "userProductController")
@RequestMapping(value = "/user/product")
public class UserProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        Page<Product> page = productService.findProducts(pageable);
        model.addAttribute("page", page);
        return "user/product.list";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam Long id, Model model){
        User user = SecurityUtils.getCurrentUser();

        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        return "user/product/view";
    }
}
