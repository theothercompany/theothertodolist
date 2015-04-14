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
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author asikprad
 */
@Controller
public class TodoController {

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

}
