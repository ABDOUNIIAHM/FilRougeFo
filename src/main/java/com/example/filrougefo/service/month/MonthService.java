package com.example.filrougefo.service.month;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.repository.MonthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MonthService implements IntMonthService{
    private final MonthRepository monthRepository;

    public List<Month> findAll() {
        return monthRepository.findAll();
    }

}
