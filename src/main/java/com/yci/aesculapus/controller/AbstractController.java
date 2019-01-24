package com.yci.aesculapus.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

public abstract class AbstractController<T> {

    @PostMapping
    public T addNew(T item) {

    }

    @GetMapping(path = "{id}")
    public T getItem(@PathVariable("id") String id) {

    }

    @GetMapping
    public Page<T> getByQuery(@QueryParam("name") String query) {

    }

    @PutMapping(path = "{id}")
    public T changeItem() {

    }


    @DeleteMapping(path = "")
    public void deleteItem() {

    }
}
