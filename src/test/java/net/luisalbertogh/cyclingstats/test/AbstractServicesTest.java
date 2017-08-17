/**
 * Abstract class with test cases.
 */
package net.luisalbertogh.cyclingstats.test;

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
        Rider rider = controller.getRiderDetails("Mikel_Landa");
        System.out.println(rider);
        Assert.notNull(rider);
    }
}
