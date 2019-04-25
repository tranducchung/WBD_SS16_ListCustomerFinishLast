package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class controllerCustomer {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
   public ModelAndView listCustomer(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute Customer customer){
        customerService.save(customer);
       ModelAndView modelAndView = new ModelAndView("create");
       modelAndView.addObject("customer",new Customer());
       modelAndView.addObject("message","New customer created successfully");
       return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView editCustomer(@ModelAttribute Customer customer){
        ModelAndView modelAndView = new ModelAndView("edit");
        customerService.save(customer);
        modelAndView.addObject("message","Edit customer success");
        return modelAndView;
    }
    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer",customerService.findById(id));
        return modelAndView;
    }
    @PostMapping("/delete-customer")
    public ModelAndView deleteCustomer(@RequestParam Long id){
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("message","Delete customer success");
        return modelAndView;
    }
//    public String index(){}
}
