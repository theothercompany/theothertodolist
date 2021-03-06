/*
 * Copyright 2014 asikprad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.theothercompany.theothertodolist.web;

import io.theothercompany.theothertodolist.model.Todo;
import io.theothercompany.theothertodolist.service.AtService;
import io.theothercompany.theothertodolist.service.HashService;
import io.theothercompany.theothertodolist.service.PriorityService;
import io.theothercompany.theothertodolist.service.TodoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author asikprad, wsams
 */
@Controller
public class TodoController {

    @Autowired
    @Qualifier("todoService")
    private TodoService todoService;

    @Autowired
    private HashService hashService;

    @Autowired
    protected AtService atService;

    @Autowired
    private PriorityService priorityService;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "todo/welcome";
    }

    @RequestMapping(value = "/rest/list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> getTodoItems() {
        StringBuilder buf = new StringBuilder();
        List<Todo> allTodos = todoService.findAll();
        if (allTodos != null) {
            for (Todo todo : todoService.findAll()) {
                buf.append(todo.getTodo());
                buf.append("\n");
            }
        }
        String what = buf.toString();
        if (what.isEmpty()) {
            what = "make a better todo list @today #fun #blocker";
        }
        Map<String, String> response = new HashMap<>();
        response.put("todos", what);
        return response;
    }

    @RequestMapping(value = "/rest/save.json", method = RequestMethod.POST, 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Map<String, String> postTodoItems(@RequestBody Map<String, String> body) {
        String[] todos = body.get("todos").split("\n");

        todoService.deleteAll();
        atService.deleteAll();
        priorityService.deleteAll();
        for (String todo : todos) {
            Todo saved = todoService.save(new Todo(todo));
            priorityService.save(saved);
            int id = saved.getId();
            String s = saved.getTodo();
            atService.parse(id, s);
            hashService.parse_and_save(id, s);
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "Todos updated");
        return response;
    }
    @RequestMapping(value = "/rest/list.txt", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getTodoItemsFile() {
        return this.getTodoItems().get("todos");
    }
    @RequestMapping(value = "/rest/save.txt", method = RequestMethod.POST, 
        produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    @Transactional
    public String postTodoItemFile(@RequestBody String body) {
        Map<String, String> stuff = new HashMap<>();
        stuff.put("todos", body);
        return this.postTodoItems(stuff).get("status");
    }

    public HashService getHashService() {
        return hashService;
    }

    public void setHashService(HashService hashService) {
        this.hashService = hashService;
    }

    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    public void setAtService(AtService atService) {
        this.atService = atService;
    }

}
