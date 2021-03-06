package cg.controller;

import cg.model.Classroom;
import cg.model.Student;
import cg.service.IClassroomService;
import cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;
    @Autowired
    private IClassroomService iclassroomService;

    @GetMapping
    public ModelAndView showStudents(@PageableDefault(value = 1) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Student> students = iStudentService.findAllStudent(pageable);
        if (students.isEmpty()) {
            modelAndView.addObject("message", "No Student !!!");
        }
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    @GetMapping("/create-student")
    public ModelAndView createProduct() {
        ModelAndView modelAndView = new ModelAndView("create");
        Iterable<Classroom> classrooms = iclassroomService.findAllClassrooms();
        modelAndView.addObject("classrooms", classrooms);
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }
    @PostMapping("/save-student")
    public ModelAndView saveProduct(@Validated @ModelAttribute Student student, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("create");
            if (bindingResult.hasFieldErrors()){
               return new ModelAndView("create");
            }
            else {
                iStudentService.saveStudent(student);
                Iterable<Classroom> classrooms = iclassroomService.findAllClassrooms();
                modelAndView.addObject("classrooms", classrooms);
                modelAndView.addObject("message", "Create Product Successfully !!!");
            }

        return modelAndView;
    }
    @GetMapping("/delete-student/{id}")
    public ModelAndView deleteStudent(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        iStudentService.deleteStudent((int) id);
        return modelAndView;
    }
    @GetMapping("/edit-student/{id}")
    public ModelAndView editProduct(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Student student = iStudentService.findById(id);
        Iterable<Classroom> classrooms = iclassroomService.findAllClassrooms();
        modelAndView.addObject("classrooms", classrooms);
        modelAndView.addObject("student", student);
        return modelAndView;
    }
    @PostMapping("/update-student/{id}")
    public ModelAndView updateProduct(@PathVariable int id, @ModelAttribute Student student) {
        student.setId(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        Student studentEdit = iStudentService.saveStudent(student);
        if (studentEdit != null) {
            Iterable<Classroom> classrooms = iclassroomService.findAllClassrooms();
            modelAndView.addObject("classrooms", classrooms);
            modelAndView.addObject("message", "Update Product Successfully !!!");
        }
        return modelAndView;
    }
    @GetMapping("/view/{id}")
    public ModelAndView detailProduct(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        Student student = iStudentService.findById(id);
        modelAndView.addObject("student", student);
        return modelAndView;
    }
    @PostMapping("/searchByName")
    public ModelAndView searchProductByName(@RequestParam("searchByName") String name, @PageableDefault(value = 1) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<Student> students;
        if (name != null) {
            students = iStudentService.getAllStudentsByName(name, pageable);
            modelAndView.addObject("searchByName", name);
        } else {
            students = iStudentService.findAllStudent(pageable);
        }

        modelAndView.addObject("students", students);

        return modelAndView;
    }

}
