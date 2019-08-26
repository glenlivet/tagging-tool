package org.glenlivet.tagging.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="corpus")
@Inheritance(strategy = InheritanceType.JOINED)
public class Corpus implements Serializable  {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
