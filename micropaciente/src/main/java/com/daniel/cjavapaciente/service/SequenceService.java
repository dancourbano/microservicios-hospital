package com.daniel.cjavapaciente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.daniel.cjavapaciente.model.Sequence;

import static org.springframework.data.mongodb.core.query.Query.*;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.*;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;

@Service
public class SequenceService {
	@Autowired
	private MongoOperations mongoOperations;

    public SequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {

        Sequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("value",1), options().returnNew(true).upsert(true),
                Sequence.class);
        return counter.getValue();

    }
}
