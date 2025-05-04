package com.example.validationdemo;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CustomerController {

    //add an InitBinder to convert trim input strings
    //remove leading and trailing white spaces
    //resolve issues for our validation
    //mine : initBinder PRE-Process all web requests coming to our controller
    // thus we can put a mothod to filtr and customize the input("inserts") data
    //in this case to get rid of the white spaces we use the string trimmer editor(remove extrat ws)
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @GetMapping("/")
    public String showForm(Model theModel){

        theModel.addAttribute("customer", new Customer());

        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processFrom(
        @Valid @ModelAttribute("customer") Customer theCustomer, BindingResult theBindingResult){

            System.out.print("last name : |" + theCustomer.getLastName() + "|");

            //to get more informations about the input errors occuring
            //ex:where it occured, the value, codes (usefull to solve) ...
            System.out.println("Binding results: " + theBindingResult);

            if(theBindingResult.hasErrors())
                return "customer-form";
            
            else
            return "customer-confirmation";
    }
}
