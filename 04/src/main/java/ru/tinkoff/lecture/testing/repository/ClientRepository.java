package ru.tinkoff.lecture.testing.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.lecture.testing.model.Client;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByLastNameLike(String lastName, Pageable page);

}
