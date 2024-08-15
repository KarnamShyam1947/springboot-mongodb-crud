package com.shyam.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.Objects;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.shyam.documents.SequenceDocument;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;


    public int getSequenceNumber(String sequenceName) {
        
        Query query = new Query(Criteria.where("sequenceName").is(sequenceName));
        
        Update update = new Update().inc("sequence", 1);
        
        SequenceDocument counter = mongoOperations
                .findAndModify(query,
                        update, options().returnNew(true).upsert(true),
                        SequenceDocument.class);

        return !Objects.isNull(counter) ? counter.getSequence() : 1;
    }
}