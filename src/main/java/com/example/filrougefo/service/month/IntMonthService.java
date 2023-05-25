package com.example.filrougefo.service.month;

import com.example.filrougefo.entity.Month;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntMonthService {

    List<Month> findAll();

}
