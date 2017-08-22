/**
 * Web GUI controller.
 */
package net.luisalbertogh.cyclingstats.controllers;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import net.luisalbertogh.cyclingstats.domain.Rider;
import net.luisalbertogh.cyclingstats.tools.RiderClassifier;
import net.luisalbertogh.cyclingstats.tools.RidersManager;

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
    
    /** Riders manager */
    @Autowired
    private RidersManager ridersManager;
    
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
        model.addAttribute("riderForm", new Rider());
        model.addAttribute("rider", new Rider());
        model.addAttribute("ridersList", new RidersList());        
        return "index";
    }
    
    /**
     * Populate classifiers
     * @return
     */
    @ModelAttribute("allClassifiers")
    public List<RiderClassifier> populateClassifiers(){
        return Arrays.asList(RiderClassifier.values());
    }
    
    /**
     * Get rider details.
     * @param rider
     * @param model
     * @return
     */
    @RequestMapping(value = "/rider")
    public String getRider(final Rider rider, final BindingResult bindingResult, Model model){
        String name = rider.getName();
        if(name != null && !name.equals("")){
            logger.info("Find details for name: " + rider.getName());
            /* Find rider */
            ResponseEntity<Rider> response = cscontroller.getRiderDetails(rider.getName());
            if(response.getStatusCodeValue() == HttpStatus.OK.value()){
                Rider theRider = response.getBody();
                if(theRider != null && theRider.getName() != null){
                    model.addAttribute("rider", theRider);
                } 
            }
            /* Rider not found */
            else if(response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
                bindingResult.addError(new ObjectError("RiderNotFound","Rider not found"));
            }
        } 
        /* Invalid name */
        else {
            bindingResult.addError(new ObjectError("InvalidName","Name is null or empty"));
        }
        /* Reset fields */
        model.addAttribute("riderForm", new Rider());
        model.addAttribute("ridersList", new RidersList());
        return "index";
    }
    
    /**
     * Get riders details.
     * @param rider
     * @param model
     * @return
     */
    @RequestMapping(value = "/findriders")
    public String findRiders(final RidersList riderList, final BindingResult bindingResult, Model model){
        String names = riderList.getRidersNames();
        if(names != null && !names.equals("")){
            logger.info("Find details for list: " + names);
            /* Find riders */
            ResponseEntity<List<Rider>> response = cscontroller.getRidersList(names);
            if(response.getStatusCodeValue() == HttpStatus.OK.value()){
                List<Rider> riders = response.getBody();
                if(riders != null && !riders.isEmpty()){
                    riders = ridersManager.orderRidersBy(riders, riderList.getClassif());
                    model.addAttribute("riderResults", riders);
                } 
            }
            /* Not found */
            else if(response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value()) {
                bindingResult.addError(new ObjectError("RiderNotFound","Rider not found"));
            }
        }
        /* Invalid input */
        else {
            bindingResult.addError(new ObjectError("InvalidName","Name is null or empty"));
        }
        /* Reset fields */
        //riderList.setRidersNames("");
        model.addAttribute("riderForm", new Rider());
        model.addAttribute("rider", new Rider());
        return "index";
    }
}
