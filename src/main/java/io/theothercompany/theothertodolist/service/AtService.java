/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.AtModel;
import io.theothercompany.theothertodolist.repository.AtRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author The Other Company
 */
@Service("atService")
public class AtService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtService.class);
    protected final Pattern atPattern = Pattern.compile("@[^\\s@;]+");
    @Autowired
    @Qualifier("atRepository")
    private AtRepository atRepository;

    @Transactional
    public AtModel save(AtModel save) throws DataAccessException {
        return atRepository.save(save);
    }

    @Transactional
    public Map<String, List<Integer>> collectTypes() throws DataAccessException {
        Map<String, List<Integer>> ret = new HashMap<>();
        for (AtModel at : atRepository.findAll()) {
            String type = at.getType();
            if (!ret.containsKey(type)) {
                ret.put(type, new ArrayList<>());
            }
            ret.get(type).add(at.getTodoId());
        }
        return ret;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAll() throws DataAccessException {
        atRepository.deleteAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<AtModel> parse(Integer todoId, String line) throws DataAccessException {
        Matcher matcher = atPattern.matcher(line);
        String atType = null;
        if (matcher.find()) {
            atType = matcher.group();
        }
        if (atType != null && !atType.isEmpty()) {
            AtModel at = new AtModel();
            at.setTodoId(todoId);
            at.setType(atType);
            return Optional.ofNullable(this.save(at));
        }
        return Optional.empty();
    }
    
    @Transactional(readOnly = true)
    public AtModel findAtForTodo(Integer todoId) throws DataAccessException {
        return atRepository.findOneByTodoId(todoId);
    }
    
    @Transactional(readOnly = true)
    public List<Integer> findTodoIdsForType(String type) throws DataAccessException {
        List<Integer> ret = new ArrayList<>();
        for (AtModel at : atRepository.findAllByType(type)) {
            ret.add(at.getTodoId());
        }
        return ret;
    }
    
}
