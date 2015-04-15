/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.HashModel;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
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
 * @author The Other Company
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {"classpath:business-config.xml"})
public class HashServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtServiceTest.class);
    @Autowired
    protected HashService hashService;
    public HashServiceTest() {
    }
    
    @Test
    public void parse_with_hash_type() {
        String line = "this is a todo with #marker";
        Integer todoId = 1;
        List<HashModel> maybeTodo = this.hashService.parse_and_save(todoId, line);
        assertEquals("#marker", maybeTodo.get(0).getTag());
    }
    
    @Test
    public void collect_hash_types() {
        String line = "this is a todo with #marker";
        Integer todoId = 2;
        this.hashService.parse_and_save(todoId, line);
        Map<String, List<Integer>> tags = this.hashService.collectTags();
        LOGGER.error("found {}", tags);
        List<Integer> todoIds = tags.get("#marker");
        assertTrue("Expected ID must be present in #marker", todoIds.contains(todoId));
    }
    
    @Test
    public void can_delete_hash_models() {
        String line = "this is a todo with #tag";
        Integer todoId = 3;
        this.hashService.parse_and_save(todoId, line);
        this.hashService.deleteAll();
        Map<String, List<Integer>> types = this.hashService.collectTags();
        assertEquals("all #tags should be gone", 0, types.size());
    }
}
