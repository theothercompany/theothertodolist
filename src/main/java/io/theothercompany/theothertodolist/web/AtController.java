/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.web;

import io.theothercompany.theothertodolist.service.AtService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author The Other Company
 */
@Controller
public class AtController {
    @Autowired
    protected AtService atService;

    @RequestMapping(value = "/rest/at/populate.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, List<Integer>> populateItems() {
        atService.parse(1, "pears @grocery");
        atService.parse(2, "Spring @dev");
        atService.parse(3, "something @test @ignored");
        return atService.collectTypes();
    }
    
    @RequestMapping(value = "/rest/at/list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, List<Integer>> getTodoItems() {
        return atService.collectTypes();
    }
}
