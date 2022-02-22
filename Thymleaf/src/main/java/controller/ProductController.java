package controller;

import model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.IProductService;
import service.ProductServiceImpl;

@Controller
public class ProductController {

    IProductService iProductService = new ProductServiceImpl();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", this.iProductService.findAll());
        return "index";
    }
    @GetMapping("/product/create")
    public String create(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create";
    }
    @PostMapping("/product/create")
    public  String create(Product product, RedirectAttributes redirect){
        int id = this.iProductService.findAll().size() + 1;
        product.setId(id);
        this.iProductService.save(product);
        redirect.addFlashAttribute("success", "Created new successfully");
        return "redirect:/";
    }
    @GetMapping("product/{id}/update")
    public  String update(@PathVariable int id, Model model){
        Product product = this.iProductService.findById(id);
        model.addAttribute("product", product);
        return "edit";
    }
    @PostMapping("/product/update")
    public  String update(Product product, RedirectAttributes redirect){
        this.iProductService.update(product.getId(), product);
        redirect.addFlashAttribute("success", "Update product successfully");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/delete")
    public String delete(@PathVariable int id, Model model){
        Product product = this.iProductService.findById(id);
        model.addAttribute("product", product);
        return "delete";
    }
    @PostMapping("/product/delete")
    public String delete(Product product, RedirectAttributes redirect){
        this.iProductService.remove(product.getId());
        redirect.addFlashAttribute("success", "Remove product successfully");
        return "redirect:/";
    }
    @GetMapping("/product/{id}/view")
    public String view(@PathVariable int id, Model model){
        Product product = this.iProductService.findById(id);
        model.addAttribute("product", product);
        return "view";
    }
}
