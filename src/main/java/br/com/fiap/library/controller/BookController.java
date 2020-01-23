package br.com.fiap.library.controller;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import br.com.fiap.library.dto.CreateBookDTO;
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

    public BookController(){
        bookDTOList.add(new BookDTO(
                1,
                "O Guia do Mochileiro das Galaxias",
                100,
                "12321321",
                ZonedDateTime.now().minusYears(40),
                new AutorDTO()
        ));
        bookDTOList.add(new BookDTO(
                2,
                "O Restaurante no Fim do Universo",
                100,
                "12321321",
                ZonedDateTime.now().minusYears(40),
                new AutorDTO()
        ));
        bookDTOList.add(new BookDTO(
                3,
                "A Vida, o Universo e Tudo Mais",
                100,
                "12321321",
                ZonedDateTime.now().minusYears(40),
                new AutorDTO()
        ));
    }

    @GetMapping
    public List<BookDTO> getAll(@RequestParam(required = false, value = "title") String titulo){
        return bookDTOList.stream()
                .filter(bookDTO -> titulo== null || bookDTO.getTitulo().startsWith(titulo))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public BookDTO findById(@PathVariable Integer id){
        return bookDTOList.stream()
                .filter(bookDTO -> bookDTO.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public BookDTO create(@RequestBody CreateBookDTO createBookDTO){
        BookDTO bookDTO = new BookDTO(createBookDTO, bookDTOList.size() + 1);
        bookDTOList.add(bookDTO);
        return bookDTO;
    }
}
