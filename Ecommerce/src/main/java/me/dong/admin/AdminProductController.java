package me.dong.admin;

import me.dong.model.domain.Product;
import me.dong.model.service.ProductService;
import me.dong.model.vo.ResponseVO;
import me.dong.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

@Controller(value = "adminProductController")
@RequestMapping(value = "/admin/product")
@SessionAttributes(value = "product")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable){
        Page<Product> page = productService.findProducts(pageable);
        model.addAttribute("page", page);
        return "product/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "product/form";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(@RequestParam Long id, Model model){
        Product product = productService.findOne(id);
        model.addAttribute("product", product);
        return "product/form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO save(@ModelAttribute Product product, @RequestParam("imageFile")MultipartFile imageFile,
                           SessionStatus sessionStatus){
        product = productService.save(product);

        // image upload
        if(imageFile != null && !imageFile.isEmpty()){
            FileUtil.upload(imageFile, product.getImageUploadPath());
            product.setImageFileName(imageFile.getOriginalFilename());
            productService.save(product);
        }
        sessionStatus.setComplete();
        return ResponseVO.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVO delete(@RequestParam Long id){
        productService.delete(id);
        return ResponseVO.ok();
    }
}
