/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.repository;

import io.theothercompany.theothertodolist.model.AtModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author The Other Company
 */
public interface  AtRepository extends JpaRepository<AtModel, String>{
    public AtModel findOneByTodoId(int todoId);
    public List<AtModel> findAllByType(String type);
}
