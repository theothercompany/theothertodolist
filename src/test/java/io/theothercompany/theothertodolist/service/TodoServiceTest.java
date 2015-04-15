/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.Todo;
import java.util.List;
import org.junit.Assert.*;
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
        Todo todo = new Todo();
        todo.setTodo("This is my first todo");

        Todo dbTodo = todoService.save(todo);

        assertEquals("This is my first todo", dbTodo.getTodo());
    }

    /**
     * Test of find method, of class TodoService.
     */
    @Test
    public void testFindAll() {
        LOGGER.debug("find All");
        Todo myTodo = new Todo();
        myTodo.setTodo("blah blah blah");

        todoService.save(myTodo);

        Todo myTodo1 = new Todo();
        myTodo1.setTodo("what what what");
        todoService.save(myTodo1);

        List<Todo> todos = todoService.findAll();
        assertTrue(todos.contains(myTodo));
        assertTrue(todos.contains(myTodo1));
    }

    @Test
    public void testFind() {
        LOGGER.debug("find");
        Todo myTodo = new Todo();
        myTodo.setTodo("asik ha");
        todoService.save(myTodo);
        List<Todo> todos = todoService.find("asik");
        assertTrue(todos.contains(myTodo));

    }

    @Test
    public void testDeleteAll() {
        LOGGER.debug("delete all");
        Todo myTodo = new Todo();
        myTodo.setTodo("asik ha");
        todoService.save(myTodo);
        List<Todo> todos = todoService.find("asik");
        assertTrue(todos.contains(myTodo));
        todoService.deleteAll();
        List<Todo> noTodos = todoService.findAll();
        assertTrue(noTodos.isEmpty());
    }
}
