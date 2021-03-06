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
package io.theothercompany.theothertodolist.service;

import io.theothercompany.theothertodolist.model.Priority;
import io.theothercompany.theothertodolist.model.Todo;
import io.theothercompany.theothertodolist.repository.PriorityRepository;
import io.theothercompany.theothertodolist.repository.TodoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asikprad
 */
@Service("priorityService")
public class PriorityService {

    @Autowired
    @Qualifier("priorityRepository")
    private PriorityRepository priorityRepository;

    public String parse(String todo){
        return todo.replaceAll("^.*\\(([A-Z])\\).*$", "$1");
    }
    public Priority save(Todo todo) {
        return this.save(new Priority(this.parse(todo.getTodo()), todo.getId()));
    }
    @Transactional
    public Priority save(Priority priority) throws DataAccessException {
        return priorityRepository.save(priority);
    }

    @Transactional
    public void deleteAll() throws DataAccessException {
        priorityRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<Priority> findAll() throws DataAccessException {
        return priorityRepository.groupByPriority();
    }
    

}
