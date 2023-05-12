package com.example.filrougefo.service.month;

import com.example.filrougefo.repository.MonthRepository;
import org.springframework.stereotype.Service;

@Service
public class MonthService implements IntMonthService{
    private final MonthRepository monthRepository;
    public MonthService(MonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }
}
