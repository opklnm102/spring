package me.dong.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 30)
    private String name;

    @Column
    private Double price;

    @Column(name = "image_file_name", length = 100)
    private String imageFileName;

    @Column(length = 10)
    private String color;

    @Lob
    private String description;
}
