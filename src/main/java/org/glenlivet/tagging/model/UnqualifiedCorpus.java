package org.glenlivet.tagging.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="unqualified_corpus")
@OnDelete(action=OnDeleteAction.NO_ACTION)
public class UnqualifiedCorpus extends Corpus {

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
