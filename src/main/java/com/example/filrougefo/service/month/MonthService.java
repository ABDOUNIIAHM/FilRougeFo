package com.example.filrougefo.service.month;

import com.example.filrougefo.repository.MonthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MonthService implements IntMonthService{
    private final MonthRepository monthRepository;

}
