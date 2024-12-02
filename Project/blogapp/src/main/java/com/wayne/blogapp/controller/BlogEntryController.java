package com.wayne.blogapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wayne.blogapp.entity.BlogEntry;

@RestController
@RequestMapping("/blog")
public class BlogEntryController {
    private Map<Long, BlogEntry> BlogEntries = new HashMap<>();

    @GetMapping
    public List<BlogEntry> getAll(){
        return new ArrayList<>(BlogEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody BlogEntry myEntry){
        BlogEntries.put(myEntry.getId(), myEntry);
        return true;
    }

}
