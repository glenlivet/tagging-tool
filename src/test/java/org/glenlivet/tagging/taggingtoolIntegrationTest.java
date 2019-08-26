package org.glenlivet.tagging;

import org.glenlivet.tagging.model.Corpus;
import org.glenlivet.tagging.model.ResolvedCorpus;
import org.glenlivet.tagging.model.UnqualifiedCorpus;
import org.glenlivet.tagging.dao.CorpusRepository;
import org.glenlivet.tagging.dao.ResolvedCorpusRepository;
import org.glenlivet.tagging.dao.UnqualifiedCorpusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TaggingToolkitApplication.class)
public class taggingtoolIntegrationTest {

    @Autowired
    private CorpusRepository corpusRepository;

    @Autowired
    private ResolvedCorpusRepository resolvedCorpusRepository;

    @Autowired
    private UnqualifiedCorpusRepository unqualifiedCorpusRepository;

    @Test
    public void contextLoads() { }

    @Test
    public void add_query() {
        //create corpora, one for each type: normal, resolved, unqualified.
        Corpus normal = new Corpus();
        normal.setName("normal");
        corpusRepository.save(normal);

        ResolvedCorpus resolved = new ResolvedCorpus();
        resolved.setName("resolved");
        resolved.setResolved("resolved");
        resolvedCorpusRepository.save(resolved);

        UnqualifiedCorpus unqualified = new UnqualifiedCorpus();
        unqualified.setName("unqualified");
        unqualified.setReason("unqualified");
        unqualifiedCorpusRepository.save(unqualified);

        //query
        // 3 records are expected in corpus table
        // 1 record in resolved table
        // 1 in unqualified table
        assertTrue(corpusRepository.count() == 3);
        assertTrue(resolvedCorpusRepository.count() == 1);
        assertTrue(unqualifiedCorpusRepository.count() == 1);
        resolvedCorpusRepository.findAll().forEach((item) -> {
            assertTrue(item.getName().equals("resolved"));
        });
        unqualifiedCorpusRepository.findAll().forEach((item) -> {
            assertTrue(item.getName().equals("unqualified"));
        });

        //delete
        // when deleting resolved, the delete action is expected to get cascaded to ancestor table "corpus", as OnDeleteAction suggested.
        // when deleting unqualified, the delete action is not expected to get cascaded to ancestor table "corpus", as OnDeleteAction suggested.
        resolvedCorpusRepository.deleteAll();
        unqualifiedCorpusRepository.deleteAll();
        //query
        //HOWEVER, OnDeleteAction.NO_ACTION is NOT working as expected, and this assertion is failed.
        assertTrue(corpusRepository.count() == 2);

    }

}
