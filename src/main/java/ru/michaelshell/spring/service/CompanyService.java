package ru.michaelshell.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.michaelshell.spring.dto.CompanyReadDto;
import ru.michaelshell.spring.listener.AccessType;
import ru.michaelshell.spring.listener.EntityEvent;
import ru.michaelshell.spring.repository.CompanyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final UserService userService;
    private final CompanyRepository companyRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Optional<CompanyReadDto> findById (Integer id) {
        return companyRepository.findById(id)
                .map(company -> {
                    eventPublisher.publishEvent(new EntityEvent(company, AccessType.READ));
                    return new CompanyReadDto(company.getId(), null);
                });
    }

}
