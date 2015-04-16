package io.theothercompany.theothertodolist.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.theothercompany.theothertodolist.model.Todo;
import io.theothercompany.theothertodolist.service.AtService;
import io.theothercompany.theothertodolist.service.HashService;
import io.theothercompany.theothertodolist.service.TodoService;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author wsams
 */
public class TodoControllerTest {

    public TodoControllerTest() {
    }

    @Test
    public void testGetTodoItems() throws Exception {
        TodoController todo = new TodoController();
        todo.setAtService(Mockito.mock(AtService.class));
        todo.setHashService(Mockito.mock(HashService.class));
        TodoService mockTodoService = Mockito.mock(TodoService.class);
        todo.setTodoService(mockTodoService);
        MockMvc mvc = standaloneSetup(todo).build();
        mvc.perform(get("/rest/list.json")).andExpect(status().isOk());
        assertTrue(true);
    }

    @Test
    public void testPostTodoItems() throws JsonProcessingException, Exception {
        TodoController todo = new TodoController();
        todo.setAtService(Mockito.mock(AtService.class));
        todo.setHashService(Mockito.mock(HashService.class));
        TodoService mockTodoService = Mockito.mock(TodoService.class);
        todo.setTodoService(mockTodoService);
        Todo t = new Todo("test");
        t.setId(42);
        Mockito.when(mockTodoService.save(Matchers.anyObject())).thenReturn(t);
        MockMvc mvc = standaloneSetup(todo).build();
        Map<String, String> r = new HashMap<>();
        r.put("todos", "buy groceries");
        ObjectMapper om = new ObjectMapper();
        mvc.perform(post("/rest/save.json").contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(r)).accept(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk());
        assertTrue(true);
    }
    
}
