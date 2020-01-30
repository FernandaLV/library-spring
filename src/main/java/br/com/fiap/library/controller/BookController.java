package br.com.fiap.library.controller;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import br.com.fiap.library.dto.CreateBookDTO;
import br.com.fiap.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    List<BookDTO> bookDTOList = new ArrayList<>();
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAll(@RequestParam(required = false, value = "title") String titulo){
        return bookService.findAll(titulo);
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable Integer id){
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody @Validated CreateBookDTO createBookDTO){
        return bookService.create(createBookDTO);
    }

    @PutMapping("{id}")
    public BookDTO update(@PathVariable Integer id, @RequestBody CreateBookDTO createBookDTO){
        return bookService.update(id, createBookDTO);
    }

    @PatchMapping("{id}")
    public BookDTO update(@PathVariable Integer id, @RequestBody AutorDTO autorDTO){
        return bookService.update(id, autorDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        bookService.delete(id);
    }

}
