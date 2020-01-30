package br.com.fiap.library.entity;

import br.com.fiap.library.dto.AutorDTO;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "TB_BOOK")
public class Book {
    @Id
    private Integer id;

    @Column
    private String titulo;

    @Column
    private Integer quantidadeDePaginas;

    @Column
    private String ISBN;

    @Column
    private ZonedDateTime dataLancamento;

    @ManyToOne
    private Autor autor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getQuantidadeDePaginas() {
        return quantidadeDePaginas;
    }

    public void setQuantidadeDePaginas(Integer quantidadeDePaginas) {
        this.quantidadeDePaginas = quantidadeDePaginas;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public ZonedDateTime getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(ZonedDateTime dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
