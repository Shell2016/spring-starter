package ru.michaelshell.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import ru.michaelshell.spring.database.entity.Company;
import ru.michaelshell.spring.integration.IntegrationTestBase;
import ru.michaelshell.spring.repository.CompanyRepository;

import javax.persistence.EntityManager;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RequiredArgsConstructor
class CompanyRepositoryTest extends IntegrationTestBase {

    private static final Integer APPLE_ID = 40;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByName("google");
        companyRepository.findAllByNameContainingIgnoreCase("a");
    }

    @Test
    void delete() {
        var optionalCompany = companyRepository.findById(APPLE_ID);
        assertThat(optionalCompany).isPresent();
        optionalCompany.ifPresent(companyRepository::delete);
        entityManager.flush();
        assertThat(companyRepository.findById(APPLE_ID)).isEmpty();
    }

    @Test
    void findById() {

        var company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void save() {

        var company = Company.builder()
                .name("Apple")
                .locales(Map.of(
                        "ru", "Яблоко описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}