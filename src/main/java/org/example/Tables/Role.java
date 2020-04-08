package org.example.Tables;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority{


    private Long id;
    private String name;
    private Set<User> users;


    public Role(){}

    public Role(Long id){
        this.id = id;
    }

    public Role(Long id, String name){
        this.name = name;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    @Override
    public String getAuthority() {
        return getName();
    }
}
