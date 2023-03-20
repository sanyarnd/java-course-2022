package ru.tinkoff.lecture.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.lecture.testing.dto.GetClientsRequest;
import ru.tinkoff.lecture.testing.model.Client;
import ru.tinkoff.lecture.testing.repository.ClientRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getClients(GetClientsRequest request) {
        return clientRepository.findAllByLastNameLike(request.lastName(), request.page());
    }

}
