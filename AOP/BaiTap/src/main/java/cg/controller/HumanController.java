package cg.controller;

import cg.model.Human;
import cg.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("home")
public class HumanController {
    @Autowired
    private IHumanService iHumanService;

    @RequestMapping
    public ModelAndView showHuman(@PageableDefault(value = 3) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Human> humans = iHumanService.findAllHuman(pageable);
        if (humans.isEmpty()){
            modelAndView.addObject("messenger", "No Human !!!");
        }
        modelAndView.addObject("humans", humans);
        return modelAndView;
    }

    @GetMapping("/create-human")
    public ModelAndView createHuman() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("human", new Human());
        return modelAndView;
    }

    @PostMapping("/save-human")
    public ModelAndView saveHuman(@Validated @ModelAttribute("human") Human human, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
        if (bindingResult.hasFieldErrors()){
            modelAndView.addObject("human",human);
            return new ModelAndView("create");
        }
        else {
            iHumanService.saveHuman(human);
            modelAndView.addObject("messenger", "Create Product Successfully !!!");
        }

        return modelAndView;
    }

    @GetMapping("/delete-human/{id}")
    public ModelAndView deleteHuman(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        iHumanService.deleteHuman((int) id);
        return modelAndView;
    }

    @GetMapping("/edit-human/{id}")
    public ModelAndView editHuman(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Human human = iHumanService.findById(id);
        modelAndView.addObject("human", human);
        return modelAndView;
    }

    @PostMapping("/update-human/{id}")
    public ModelAndView updateHuman(@Validated  Human human, BindingResult bindingResult, @PathVariable int id) {
        human.setId(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("human", human);
            return new ModelAndView("edit");
        } else {
            iHumanService.saveHuman(human);
            modelAndView.addObject("messenger", "Update Human Successfully !!!");
            return modelAndView;
        }

    }

    @GetMapping("/view/{id}")
    public ModelAndView detailHuman(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Human human = iHumanService.findById(id);
        modelAndView.addObject("human", human);
        return modelAndView;
    }
    @PostMapping("/searchByName")
    public ModelAndView searchProductByName(@RequestParam("searchByName") String name, @PageableDefault(value = 1) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Human> humans;
        if (name != null) {
            humans = iHumanService.getAllHumanByName(name, pageable);
            modelAndView.addObject("searchByName", name);
        } else {
            humans = iHumanService.findAllHuman(pageable);
        }

        modelAndView.addObject("humans", humans);

        return modelAndView;
    }

}
