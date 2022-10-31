package ru.michaelshell.spring.integration.database.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.michaelshell.spring.database.entity.Role;
import ru.michaelshell.spring.dto.UserFilter;
import ru.michaelshell.spring.integration.IntegrationTestBase;
import ru.michaelshell.spring.repository.UserRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
class UserRepositoryTest extends IntegrationTestBase {

    private final UserRepository userRepository;

    @Test
    void checkBatch() {
        var users = userRepository.findAll();
        userRepository.updateCompanyAndRole(users);
        System.out.println();
    }

    @Test
    void checkJdbcTemplate() {
        var users = userRepository.findAllByCompanyIdAndRole(1, Role.USER);
        assertThat(users).hasSize(1);
    }

    @Test
    void checkAuditing() {
        var ivan = userRepository.findById(1L).get();
        ivan.setBirthDate(ivan.getBirthDate().plusYears(1L));
        userRepository.flush();
        System.out.println();
    }

    @Test
    void checkCustomImplementation() {
        var userFilter = new UserFilter(null, "ov", LocalDate.now());
        var users = userRepository.findAllByFilter(userFilter);
        assertThat(users).hasSize(4);
        System.out.println();
    }

    @Test
    void checkProjections() {
        var users = userRepository.findAllByCompanyId(1);
        assertThat(users).hasSize(2);
    }

    @Test
    void checkPageable() {
        var pageable = PageRequest.of(0, 2, Sort.by("id"));
        var slice = userRepository.findAllBy(pageable);
        slice.forEach(user -> System.out.println(user.getCompany().getName()));
        while (slice.hasNext()) {
            slice = userRepository.findAllBy(slice.nextPageable());
            slice.forEach(user -> System.out.println(user.getCompany().getName()));
            System.out.println(slice.getTotalElements());
            System.out.println(slice.getTotalPages());
        }
    }

    @Test
    void checkSort() {
        var sortBy = Sort.by("firstname").and(Sort.by("lastname"));
//        var sort = Sort.sort(User.class);
//        var sortBy = sort.by(User::getFirstname).and(sort.by(User::getLastname));
        var top3 = userRepository.findTop3ByBirthDateBefore(LocalDate.now(), sortBy);
        assertThat(top3).hasSize(3);
    }

    @Test
    void checkFindTop() {
        var topUser = userRepository.findTopByOrderByIdDesc();
        assertTrue(topUser.isPresent());
        topUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkQuery() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }

    @Test
    void checkUpdateRole() {
        var ivan = userRepository.getReferenceById(1L);
        assertSame(ivan.getRole(), Role.ADMIN);
        ivan.setBirthDate(LocalDate.now());

        var count = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(count, 2);

        var sameIvan = userRepository.getReferenceById(1L);
        assertSame(sameIvan.getRole(), Role.USER);
    }

}