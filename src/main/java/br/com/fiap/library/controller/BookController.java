package br.com.fiap.library.controller;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @GetMapping
    public List<BookDTO> getAll(){
        List<BookDTO> bookDTOList = new ArrayList<>();
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

        return bookDTOList;
    }
}
