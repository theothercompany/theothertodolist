/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.Priority;
import io.theothercompany.theothertodolist.model.Todo;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author asik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:business-config.xml"})
public class PriorityServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityServiceTest.class);

    @Autowired
    protected PriorityService priorityService;

    public PriorityServiceTest() {
    }

    /**
     * Test of save method, of class TodoService.
     */
    @Test
    public void testSave() {
        LOGGER.debug("save");
        Priority p = new Priority("A", 1234);
       
        priorityService.save(p);
        
        assertEquals("A", p.getType());
    }

    /**
     * Test of find method, of class TodoService.
     */
    @Test
    public void testFindAllInOrderOfPriority() {
        LOGGER.debug("find All priority based");
        Priority p = new Priority("A", 1234);
       
        priorityService.save(p);
        Priority p1 = new Priority("B", 123);
       
        priorityService.save(p1);
        Priority p2 = new Priority("A", 12346);
       
        priorityService.save(p2);
        
        Priority p3 = new Priority("C", 17);
       
        priorityService.save(p3);
        assertTrue(priorityService.findAll().contains(p3));
    }

  
    @Test
    public void testDeleteAll() {
       
        LOGGER.debug("delete all");
       Priority p = new Priority("A", 1234);
       
        priorityService.save(p);
        priorityService.deleteAll();
        assertTrue(priorityService.findAll().isEmpty());
               
    }
}
