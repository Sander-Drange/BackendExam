package com.example.BackendExam.ServiceTesting;

import com.example.BackendExam.model.Subassembly;
import com.example.BackendExam.repository.SubassemblyRepository;
import com.example.BackendExam.service.SubassemblyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SubassemblyServiceTest {

    @Mock
    private SubassemblyRepository subassemblyRepository;

    @InjectMocks
    private SubassemblyService subassemblyService;

    @Test
    public void testGetSubassemblies() {
        List<Subassembly> mockSubassemblies = List.of(new Subassembly(), new Subassembly());

        when(subassemblyRepository.findAll()).thenReturn(mockSubassemblies);

        List<Subassembly> result = subassemblyService.getSubassemblies();

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
