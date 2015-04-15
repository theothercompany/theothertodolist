/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.web;

import io.theothercompany.theothertodolist.service.AtService;
import io.theothercompany.theothertodolist.service.HashService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author black_000
 */
public class HashController {
    @Autowired
    protected HashService hashService;

    @RequestMapping(value = "/rest/hash/populate.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, List<Integer>> populateItems() {
        hashService.parse_and_save(1, "pears @grocery #shopping #krogers");
        hashService.parse_and_save(2, "Spring @dev #research");
        hashService.parse_and_save(3, "something @test #shopping #fries");
        return hashService.collectTags();
    }
    
    @RequestMapping(value = "/rest/hash/list.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, List<Integer>> getTodoItems() {
        return hashService.collectTags();
    }
}
