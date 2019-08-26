package org.glenlivet.tagging.dao;

import org.glenlivet.tagging.model.Corpus;
import org.springframework.data.repository.CrudRepository;

public interface CorpusRepository extends CrudRepository<Corpus, Long> {
}
