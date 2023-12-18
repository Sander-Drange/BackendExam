package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.SubassemblyRepository;
import com.example.BackendExam.service.SubassemblyService;
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
public class SubassemblyServiceTesting {

    @Mock
    private SubassemblyRepository subassemblyRepository;

    @InjectMocks
    private SubassemblyService subassemblyService;

    @Test
    public void testGetSubassembliesWithPagination() {
        int page = 0;
        int size = 2;
        List<Subassembly> mockSubassemblies = List.of(new Subassembly(), new Subassembly());

        when(subassemblyRepository.findAll(PageRequest.of(page, size))).thenReturn(new PageImpl<>(mockSubassemblies));

        List<Subassembly> result = subassemblyService.getSubassemblies(page, size);

        assertThat(result).isEqualTo(mockSubassemblies);
    }


    @Test
    public void testFindById() {
        Long subassemblyId = 1L;
        Subassembly mockSubassembly = new Subassembly();

        when(subassemblyRepository.findById(subassemblyId)).thenReturn(Optional.of(mockSubassembly));

        Optional<Subassembly> result = subassemblyService.findById(subassemblyId);

        assertThat(result).contains(mockSubassembly);
    }
}
