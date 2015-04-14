package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.AtModel;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class AtServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtServiceTest.class);
    @Autowired
    protected AtService atService;
    public AtServiceTest() {
    }
    
    @Test
    public void parse_with_at_marker() {
        String line = "this is a todo with @marker";
        Integer todoId = 1;
        Optional<AtModel> maybeTodo = this.atService.parse(todoId, line);
        assertEquals("@marker", maybeTodo.get().getType());
    }
        
}
