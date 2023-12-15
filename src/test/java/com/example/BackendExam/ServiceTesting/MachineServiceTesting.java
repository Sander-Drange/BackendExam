package com.example.BackendExam.ServiceTesting;
import com.example.BackendExam.model.Machine;
import com.example.BackendExam.repository.MachineRepository;
import com.example.BackendExam.service.MachineService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MachineServiceTesting {

    @Mock
    private MachineRepository machineRepository;

    @InjectMocks
    private MachineService machineService;

    @Test
    public void testFindAll() {
        Machine machine1 = new Machine();
        Machine machine2 = new Machine();
        List<Machine> machines = Arrays.asList(machine1, machine2);

        when(machineRepository.findAll()).thenReturn(machines);
        List<Machine> result = machineService.findAll();

        assertEquals(2, result.size());
        verify(machineRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long machineId = 1L;
        Machine machine = new Machine();
        when(machineRepository.findById(machineId)).thenReturn(Optional.of(machine));

        Optional<Machine> result = machineService.findById(machineId);

        assertTrue(result.isPresent());
        assertEquals(machine, result.get());
        verify(machineRepository, times(1)).findById(machineId);
    }

    @Test
    public void testSaveMachine() {
        Machine machine = new Machine();
        when(machineRepository.save(machine)).thenReturn(machine);

        Machine savedMachine = machineService.save(machine);

        assertNotNull(savedMachine);
        verify(machineRepository, times(1)).save(machine);
    }
}
