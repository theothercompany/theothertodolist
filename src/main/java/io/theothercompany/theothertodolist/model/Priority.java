/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.theothercompany.theothertodolist.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author asik
 */
@Entity
@Table(name = "priority")
public class Priority {
    @Id
    @GeneratedValue()
    @Column(name="pid")
    int pid;
    
    @Column
    String type;

    @Column
    Integer todoId;

    public Priority() {
    }

    public Priority(String type, Integer todoId) {
        this.type = type.toUpperCase();
        this.todoId = todoId;
    }

    
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return "Priority{" + "pid=" + pid + ", type=" + type + ", todoId=" + todoId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.type);
        hash = 53 * hash + Objects.hashCode(this.todoId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Priority other = (Priority) obj;
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.todoId, other.todoId)) {
            return false;
        }
        return true;
    }
    

    
}
