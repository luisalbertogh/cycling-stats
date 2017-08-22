/**
 * Web GUI controller.
 */
package net.luisalbertogh.cyclingstats.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import net.luisalbertogh.cyclingstats.domain.Rider;

/**
 * @author loga
 *
 */
@Controller
public class WebController {
    /** A logger reference */
    private Logger logger;
    
    /** Stats services */
    @Autowired
    private CSController cscontroller;
    
    /**
     * Default constructor.
     */
    public WebController(){
        /* Logger */
        logger = LoggerFactory.getLogger(this.getClass());                
    }
    
    /**
     * Home page.
     * @param model
     * @return
     */
    @RequestMapping(value = "/")
    public String goHome(Model model){
        model.addAttribute("rider", new Rider());
        return "index";
    }
    
    /**
     * Get rider details.
     * @param rider
     * @param model
     * @return
     */
    @RequestMapping(value = "/rider", produces = "application/json")
    public String getRider(final Rider rider, final BindingResult bindingResult, Model model){
        String name = rider.getName();
        if(name != null && name != ""){
            logger.info("Find details for name: " + rider.getName());
            Rider theRider = cscontroller.getRiderDetails(rider.getName());
            if(theRider != null && theRider.getName() != null){
                model.addAttribute("rider", theRider);
            } else {
                bindingResult.addError(new ObjectError("RiderNotFound","Rider not found"));
            }
        } else {
            bindingResult.addError(new ObjectError("InvalidName","Name is null or empty"));
        }
        return "index";
    }
}
