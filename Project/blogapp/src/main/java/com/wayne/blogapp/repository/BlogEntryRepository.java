package com.wayne.blogapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.wayne.blogapp.entity.BlogEntry;

public interface BlogEntryRepository extends MongoRepository<BlogEntry, ObjectId>{
    
}
