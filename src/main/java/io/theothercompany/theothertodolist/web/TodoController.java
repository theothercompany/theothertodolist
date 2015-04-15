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
import io.theothercompany.theothertodolist.service.TodoService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
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
    protected AtService atService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "todo/welcome";
    }

    @RequestMapping(value = "/rest/list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> getTodoItems() {
        Map<String, String> response = new HashMap<>();
        response.put("todos", "Take out the garbage @home #trash #compost\nBuy bananas @grocery #banana #food #fruit");
        return response;
    }

    @RequestMapping(value = "/rest/list.json", method = RequestMethod.POST, 
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> postTodoItems(@RequestBody Map<String, String> body) {
        String[] todos = body.get("todos").split("\n");

        for (String todo : todos) {
            Todo saved = todoService.save(new Todo(todo));
            atService.parse(saved.getId(), saved.getTodo());
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "Todos updated");
        return response;
    }

}
