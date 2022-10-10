package ru.michaelshell.spring.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import ru.michaelshell.spring.bpp.Auditing;
import ru.michaelshell.spring.bpp.InjectBean;
import ru.michaelshell.spring.bpp.Transaction;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.spring.database.pool.entity.Company;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Auditing
@Transaction
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool2;
    private final List<ConnectionPool> pool;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    public void init() {
        log.info("init company repository... (@PostConstruct)");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }

}
