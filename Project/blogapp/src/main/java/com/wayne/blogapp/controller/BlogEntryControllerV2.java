package com.wayne.blogapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getAll(){
        List<BlogEntry> all = blogEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<BlogEntry> createEntry(@RequestBody BlogEntry myEntry){
        try {
            myEntry.setDate(LocalDateTime.now());
            blogEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
        }
        
    }
    @GetMapping("id/{id}")
    public ResponseEntity<BlogEntry> getBlogEntryById(@PathVariable ObjectId id){
        Optional<BlogEntry> BlogEntry = blogEntryService.findById(id);
        if(BlogEntry.isPresent()){
            return new ResponseEntity<>(BlogEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<BlogEntry>  deleteBlogEntryById(@PathVariable ObjectId id){
        blogEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @SuppressWarnings("unlikely-arg-type")
    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateBlogEntryById(@PathVariable ObjectId id, @RequestBody BlogEntry newEntry){
        BlogEntry old = blogEntryService.findById(id).orElse(null);
        if(old!= null){
            old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!= null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            blogEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

