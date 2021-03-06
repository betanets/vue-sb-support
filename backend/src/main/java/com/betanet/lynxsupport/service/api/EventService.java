package com.betanet.lynxsupport.service.api;

import com.betanet.lynxsupport.domain.Event;
import com.betanet.lynxsupport.domain.bean.EventBean;
import com.betanet.lynxsupport.domain.bean.ResultBean;
import com.betanet.lynxsupport.domain.bean.YearSimpleSelectBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    Page<Event> getEvents(Pageable pageable, String dateFrom, String dateTo);

    List<YearSimpleSelectBean> getEventYears();

    boolean createEvent(EventBean eventBean);

    List<ResultBean> getResults(Integer year, Integer quarter);
}
