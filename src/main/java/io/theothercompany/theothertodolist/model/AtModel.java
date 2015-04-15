/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author The Other Company
 */
@Entity
@Table(name = "atTable")
public class AtModel implements Serializable {
    @Id
    @GeneratedValue()
    @Column(name="atId")
    int id;
    
    @Column
    String type;

    @Column
    Integer todoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoIds) {
        this.todoId = todoIds;
    }


}
