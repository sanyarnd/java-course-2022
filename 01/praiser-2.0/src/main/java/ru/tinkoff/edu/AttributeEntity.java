package ru.tinkoff.edu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "attribute")
public class AttributeEntity {

    @Id
    private Long id;

    private String name;


    public String getName() {
        return name;
    }

}
