package nl.vng.realisatie.haalcentraal.core.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import nl.vng.realisatie.haalcentraal.core.model.Persistable;

@NoRepositoryBean
public interface BaseRepository<T extends Persistable> extends PagingAndSortingRepository<T, Long>, QuerydslPredicateExecutor<T> {
}
