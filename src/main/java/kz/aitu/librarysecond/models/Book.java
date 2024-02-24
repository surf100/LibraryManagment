package kz.aitu.librarysecond.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;
    private String name;
    private int year_of_publication;
    private String type;
    private int number_of_readers;
    private float price;
    private boolean has_a_price;
}
