package org.glenlivet.tagging.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "resolved_corpus")
@OnDelete(action=OnDeleteAction.CASCADE)
public class ResolvedCorpus extends Corpus {

    private String resolved;

    public String getResolved() {
        return resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }
}
