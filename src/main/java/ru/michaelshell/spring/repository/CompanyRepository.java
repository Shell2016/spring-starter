package ru.michaelshell.spring.repository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.michaelshell.spring.bpp.Auditing;
import ru.michaelshell.spring.bpp.InjectBean;
import ru.michaelshell.spring.bpp.Transaction;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.spring.database.pool.entity.Company;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Auditing
@Transaction
public class CompanyRepository implements CrudRepository<Integer, Company> {

//    @InjectBean
//    @Autowired
//    @Qualifier("pool2")
    private ConnectionPool pool2;

    @Autowired
    private List<ConnectionPool> pool;

    @PostConstruct
    public void init() {
        System.out.println("init company repository... (@PostConstruct)");
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

    @Autowired
    public void setPool2(ConnectionPool pool2) {
        this.pool2 = pool2;
    }
}
