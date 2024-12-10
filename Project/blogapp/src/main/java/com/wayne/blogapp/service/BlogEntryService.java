package com.wayne.blogapp.service;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wayne.blogapp.entity.BlogEntry;
import com.wayne.blogapp.repository.BlogEntryRepository;

@Component
public class BlogEntryService {
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    public void saveEntry(BlogEntry blogEntry){
        blogEntryRepository.save(blogEntry);
    }
    public List<BlogEntry> getAll(){
        return blogEntryRepository.findAll();
    }
    public Optional<BlogEntry> findById(ObjectId id){
        return blogEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id){
        blogEntryRepository.deleteById(id);
    }
}
