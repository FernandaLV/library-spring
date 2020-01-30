package br.com.fiap.library.service;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import br.com.fiap.library.dto.CreateBookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    List<BookDTO> bookDTOList = new ArrayList<>();

    public BookServiceImpl(){
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

    @Override
    public List<BookDTO> findAll(String titulo) {
        return bookDTOList.stream()
                .filter(bookDTO -> titulo== null || bookDTO.getTitulo().startsWith(titulo))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findById(Integer id){
        return bookDTOList.stream()
                .filter(bookDTO -> bookDTO.getId().equals(id))
            .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public BookDTO create(CreateBookDTO createBookDTO) {
        BookDTO bookDTO = new BookDTO(createBookDTO, bookDTOList.size() + 1);
        bookDTOList.add(bookDTO);
        return bookDTO;
    }

    @Override
    public BookDTO update(Integer id, CreateBookDTO createBookDTO) {
        BookDTO bookDTO = findById(id);
        bookDTO.setTitulo(createBookDTO.getTitulo());
        bookDTO.setDataLancamento(createBookDTO.getDataLancamento());
        bookDTO.setQuantidadeDePaginas(createBookDTO.getQuantidadeDePaginas());
        bookDTO.setISBN(createBookDTO.getISBN());

        return bookDTO;
    }

    @Override
    public BookDTO update(Integer id, AutorDTO autorDTO) {

        BookDTO bookDTO = findById(id);
        bookDTO.setAutor(autorDTO);
        return bookDTO;
    }

    @Override
    public void delete(Integer id) {
        BookDTO bookDTO = findById(id);
        bookDTOList.remove(bookDTO);
    }
}
