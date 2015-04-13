/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.Todo;
import org.junit.Assert;
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
public class TodoServiceTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceTest.class);

    @Autowired
    protected TodoService todoService;
    public TodoServiceTest() {
    }

    /**
     * Test of save method, of class TodoService.
     */
    @Test
    public void testSave() {
        LOGGER.debug("save");
        Todo user = new Todo();
        user.setEmail("asikpradhan@gmail.com");

        todoService.save(user);

        Assert.assertEquals("asikpradhan@gmail.com", todoService.find("asikpradhan@gmail.com").getEmail());
    }

    /**
     * Test of find method, of class TodoService.
     */
    @Test
    public void testFind() {
        LOGGER.debug("find");
        Todo user = new Todo();
        user.setEmail("asikpradhan@gmail.com");

        todoService.save(user);

        Assert.assertEquals("asikpradhan@gmail.com", todoService.find("asikpradhan@gmail.com").getEmail());
    }
    
}
