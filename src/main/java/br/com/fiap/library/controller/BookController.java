package br.com.fiap.library.controller;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import br.com.fiap.library.dto.CreateBookDTO;
import br.com.fiap.library.service.BookServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("books")
public class BookController {

    List<BookDTO> bookDTOList = new ArrayList<>();
    BookServiceImpl bookService = new BookServiceImpl();

    public BookController(){

    }

    @GetMapping
    public List<BookDTO> getAll(@RequestParam(required = false, value = "title") String titulo){
        return bookService.findAll(titulo);
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable Integer id){
        return bookDTOList.stream()
                .filter(bookDTO -> bookDTO.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody CreateBookDTO createBookDTO){
        BookDTO bookDTO = new BookDTO(createBookDTO, bookDTOList.size() + 1);
        bookDTOList.add(bookDTO);
        return bookDTO;
    }

    @PutMapping("{id}")
    public BookDTO update(@PathVariable Integer id, @RequestBody CreateBookDTO createBookDTO){

        BookDTO bookDTO = findById(id);
        bookDTO.setTitulo(createBookDTO.getTitulo());
        bookDTO.setDataLancamento(createBookDTO.getDataLancamento());
        bookDTO.setQuantidadeDePaginas(createBookDTO.getQuantidadeDePaginas());
        bookDTO.setISBN(createBookDTO.getISBN());

        return bookDTO;
    }

    @PatchMapping("{id}")
    public BookDTO update(@PathVariable Integer id, @RequestBody AutorDTO autorDTO){
        BookDTO bookDTO = findById(id);
        bookDTO.setAutor(autorDTO);
        return bookDTO;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        BookDTO bookDTO = findById(id);
        bookDTOList.remove(bookDTO);
    }

}
