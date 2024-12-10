package com.wayne.blogapp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.wayne.blogapp.entity.BlogEntry;
import com.wayne.blogapp.repository.BlogEntryRepository;

public class BlogEntryService {
    @Autowired
    private BlogEntryRepository blogEntryRepository;

    public void saveEntry(BlogEntry blogEntry){
        blogEntryRepository.save(blogEntry);
    }
}
