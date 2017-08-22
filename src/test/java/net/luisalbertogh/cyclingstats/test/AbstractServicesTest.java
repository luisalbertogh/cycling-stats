/**
 * Abstract class with test cases.
 */
package net.luisalbertogh.cyclingstats.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import net.luisalbertogh.cyclingstats.controllers.CSController;
import net.luisalbertogh.cyclingstats.domain.Rider;
import net.luisalbertogh.cyclingstats.tools.RidersManager;

/**
 * @author loga
 *
 */
public abstract class AbstractServicesTest {
    /** CS controller */
    @Autowired
    protected CSController controller;
    
    /** Riders manager */
    @Autowired
    protected RidersManager ridersManager;
    
    /**
     * Test.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getRiderDetails(){
        ResponseEntity<Rider> rider = controller.getRiderDetails("Samuel_Sanchez");
        if(rider.getStatusCodeValue() == HttpStatus.OK.value()){
            System.out.println(rider.getBody());
            Assert.notNull(rider.getBody());
        }
    }
    
    /**
     * Test.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getRiderList(){
        ResponseEntity<List<Rider>> riders = controller.getRidersList("Alberto_Contador,Samuel_Sanchez,Vincenzo_Nibali,Nairo_Quintana");
        if(riders.getStatusCodeValue() == HttpStatus.OK.value()){
            System.out.println(riders.getBody());
            Assert.notEmpty(riders.getBody());
        }
    }
    
    /**
     * Test.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void orderRiderList(){
        ResponseEntity<List<Rider>> riders = controller.getRidersList("Alberto_Contador,Samuel_Sanchez,Vincenzo_Nibali,Nairo_Quintana");
        if(riders.getStatusCodeValue() == HttpStatus.OK.value()){
            List<Rider> newriders = ridersManager.orderRidersBy(riders.getBody(), "gc");
            System.out.println(newriders);
            Assert.notEmpty(newriders);
        }
    }
}
