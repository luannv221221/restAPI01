package com.ra.controller;

import com.ra.entity.Category;
import com.ra.service.CategoryService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> list = categoryService.findAll();
//        return new ResponseEntity<>(list, HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id){
        Category category = categoryService.findById(id);
        if(category == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(category,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
        Category categoryNew;
        try {
            categoryNew = categoryService.save(category);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<>(categoryNew,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category,@PathVariable("id") Integer id){

        if(categoryService.findById(id) != null){
            Category categoryNew = categoryService.save(category);
            return new ResponseEntity<>(categoryNew,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Integer id){
        if(categoryService.findById(id) != null){
                categoryService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
