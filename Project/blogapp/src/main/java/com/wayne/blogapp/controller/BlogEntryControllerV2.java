package com.wayne.blogapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wayne.blogapp.entity.BlogEntry;
import com.wayne.blogapp.service.BlogEntryService;

@RestController
@RequestMapping("/blog")
public class BlogEntryControllerV2 {

    @Autowired
    private BlogEntryService blogEntryService;

    @GetMapping
    public List<BlogEntry> getAll(){
        return blogEntryService.getAll();
    }
    @PostMapping
    public BlogEntry createEntry(@RequestBody BlogEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        blogEntryService.saveEntry(myEntry);
        return myEntry;
    }
    @GetMapping("id/{id}")
    public BlogEntry getBlogEntryById(@PathVariable ObjectId id){
        return blogEntryService.findById(id).orElse(null);
    }
    @DeleteMapping("id/{id}")
    public boolean deleteBlogEntryById(@PathVariable ObjectId id){
        blogEntryService.deleteById(id);
        return true;
    }
    @SuppressWarnings("unlikely-arg-type")
    @PutMapping("/id/{id}")
    public BlogEntry updateBlogEntryById(@PathVariable ObjectId id, @RequestBody BlogEntry newEntry){
        BlogEntry old = blogEntryService.findById(id).orElse(null);
        if(old!= null){
            old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!= null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
        }
        blogEntryService.saveEntry(old);
        return old;
    }
}

