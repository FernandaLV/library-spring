package br.com.fiap.library.service;

import br.com.fiap.library.dto.AutorDTO;
import br.com.fiap.library.dto.BookDTO;
import br.com.fiap.library.dto.CreateBookDTO;
import br.com.fiap.library.entity.Book;
import br.com.fiap.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    List<BookDTO> bookDTOList = new ArrayList<>();

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> findAll(String titulo) {

        if (titulo != null){
            return bookRepository.findAllByTituloStartsWith(titulo)
                    .stream()
                    .map(BookDTO::new)
                    .collect(Collectors.toList());
        }

        return bookRepository.findAll()
                .stream()
                .map(BookDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO findById(Integer id){

        return bookRepository.findById(id).map(BookDTO::new)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public BookDTO create(CreateBookDTO createBookDTO) {
        Book book = new Book(createBookDTO);
        Book saveBook = bookRepository.save(book);
        return new BookDTO(saveBook);
    }

    @Override
    public BookDTO update(Integer id, CreateBookDTO createBookDTO) {

        Book book = bookRepository.findById(id).get();

        book.setTitulo(createBookDTO.getTitulo());
        book.setDataLancamento(createBookDTO.getDataLancamento());
        book.setQuantidadeDePaginas(createBookDTO.getQuantidadeDePaginas());
        book.setISBN(createBookDTO.getISBN());

        Book saveBook = bookRepository.save(book);
        return new BookDTO(saveBook);
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
