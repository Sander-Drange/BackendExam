package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.PartEntities;
import com.example.BackendExam.repository.PartEntitiesRepository;
import com.example.BackendExam.service.PartEntitiesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PartEntityServiceTesting {

    @Mock
    private PartEntitiesRepository partEntityRepository;

    @InjectMocks
    private PartEntitiesService partEntityService;

    @Test
    public void testGetPartEntitiesWithPagination() {
        int page = 0;
        int size = 2;
        List<PartEntities> mockPartEntities = List.of(new PartEntities(), new PartEntities());

        when(partEntityRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(mockPartEntities));

        List<PartEntities> result = partEntityService.getPartEntities(page, size);

        assertThat(result).isEqualTo(mockPartEntities);
    }

    @Test
    public void testFindById() {
        Long partEntityId = 1L;
        PartEntities mockPartEntity = new PartEntities();

        when(partEntityRepository.findById(partEntityId)).thenReturn(Optional.of(mockPartEntity));

        Optional<PartEntities> result = partEntityService.findById(partEntityId);

        assertThat(result).contains(mockPartEntity);
    }
}
