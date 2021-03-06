package com.betanet.lynxsupport.repository;

import com.betanet.lynxsupport.domain.User;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User, Integer>, QuerydslPredicateExecutor<User> {
}
