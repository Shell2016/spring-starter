package ru.michaelshell.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestConstructor;
import ru.michaelshell.spring.config.DatabaseProperties;
import ru.michaelshell.spring.dto.CompanyReadDto;
import ru.michaelshell.spring.integration.annotation.IT;
import ru.michaelshell.spring.service.CompanyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ApplicationRunner.class, initializers = ConfigDataApplicationContextInitializer.class)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;

    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {

        var result = companyService.findById(COMPANY_ID);
        assertTrue(result.isPresent());
        var expectedResult = new CompanyReadDto(COMPANY_ID);
        result.ifPresent(actual -> assertEquals(actual, expectedResult));
    }
}
