/**
 * Abstract class with test cases.
 */
package net.luisalbertogh.cyclingstats.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import net.luisalbertogh.cyclingstats.controllers.CSController;
import net.luisalbertogh.cyclingstats.domain.Rider;

/**
 * @author loga
 *
 */
public abstract class AbstractServicesTest {
    /** CS controller */
    @Autowired
    protected CSController controller;
    
    /**
     * Test.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getRiderDetails(){
        Rider rider = controller.getRiderDetails("Samuel_Sanchez");
        System.out.println(rider);
        Assert.notNull(rider);
    }
    
    /**
     * Test.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void getRiderList(){
        List<Rider> riders = controller.getRidersList("Alberto_Contador,Samuel_Sanchez,Vincenzo_Nibali,Nairo_Quintana");
        System.out.println(riders);
        Assert.notEmpty(riders);
    }
}
