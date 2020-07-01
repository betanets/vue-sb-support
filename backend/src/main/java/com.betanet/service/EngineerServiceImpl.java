package com.betanet.service;

import com.betanet.domain.Engineer;
import com.betanet.domain.QEngineer;
import com.betanet.repository.EngineerRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EngineerServiceImpl implements EngineerService {

    @Autowired
    private EngineerRepository engineerRepository;

    private QEngineer qEngineer = QEngineer.engineer;

    @Override
    public Page<Engineer> getEngineers(Pageable pageable) {
        Pageable withSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "fio");
        BooleanBuilder where = new BooleanBuilder();
        where.and(qEngineer.deleted.isFalse());
        return engineerRepository.findAll(where, withSort);
    }
}
