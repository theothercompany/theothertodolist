/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Index;

/**
 *
 * @author The Other Company
 */
@Entity
@Table(name = "atTable")
public class AtModel implements Serializable {
    @Id
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
