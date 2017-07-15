package com.foochane.repository;

import com.foochane.domain.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by fucheng on 2017/7/15.
 */
public interface PersonPagingAndSortingRepository extends PagingAndSortingRepository<Person,Integer> {
}
