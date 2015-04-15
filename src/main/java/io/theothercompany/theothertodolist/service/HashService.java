/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.AtModel;
import io.theothercompany.theothertodolist.model.HashModel;
import io.theothercompany.theothertodolist.repository.AtRepository;
import io.theothercompany.theothertodolist.repository.HashRepository;
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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author The Other Company
 */
@Service("hashService")
public class HashService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashService.class);
    protected final Pattern hashPattern = Pattern.compile("#[^\\s@;]+");
    @Autowired
    @Qualifier("hashRepository")
    private HashRepository hashRepository;

    @Transactional
    public HashModel save(HashModel save) throws DataAccessException {
        return hashRepository.save(save);
    }

    @Transactional
    public Map<String, List<Integer>> collectTags() throws DataAccessException {
        Map<String, List<Integer>> ret = new HashMap<>();
        for (HashModel hash : hashRepository.findAll()) {
            String tag = hash.getTag();
            if (!ret.containsKey(tag)) {
                ret.put(tag, new ArrayList<>());
            }
            ret.get(tag).add(hash.getTodoId());
        }
        return ret;
    }
    
    @Transactional
    public void deleteAll() throws DataAccessException {
        hashRepository.deleteAll();
    }

    @Transactional
    public List<HashModel> parse_and_save(Integer todoId, String line) throws DataAccessException {
        List ret = new ArrayList<>();
        Matcher matcher = hashPattern.matcher(line);
        String hashTag = null;
        while (matcher.find()) {
            hashTag = matcher.group();
            if (hashTag != null && !hashTag.isEmpty()) {
                HashModel hash = new HashModel();
                hash.setTodoId(todoId);
                hash.setTag(hashTag);
                this.save(hash);
                ret.add(hash);
            }
        }
        return ret;
    }
    
    @Transactional(readOnly = true)
    public List<HashModel> findTagsForTodo(Integer todoId) throws DataAccessException {
        return hashRepository.findAllByTodoId(todoId);
    }
    
    @Transactional(readOnly = true)
    public List<Integer> findTodoIdsForTag(String tag) throws DataAccessException {
        List<Integer> ret = new ArrayList<>();
        for (HashModel hash : hashRepository.findAllByTag(tag)) {
            ret.add(hash.getTodoId());
        }
        return ret;
    }
}
