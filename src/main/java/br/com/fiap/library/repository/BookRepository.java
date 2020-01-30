package br.com.fiap.library.repository;

import br.com.fiap.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTituloStartsWith(String titulo);

    @Query("select b from Book b where b.titulo= :titulo")
    List<Book> findByTitulo(@Param("titulo") String titulo);
}
