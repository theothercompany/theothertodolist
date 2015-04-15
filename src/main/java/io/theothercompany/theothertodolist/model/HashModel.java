/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.model;

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
@Table(name = "hash_t")
public class HashModel {
    @Id
    @GeneratedValue()
    @Column(name="atId")
    int id;
    
    @Column
    String tag;

    @Column
    Integer todoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoIds) {
        this.todoId = todoIds;
    }
}
