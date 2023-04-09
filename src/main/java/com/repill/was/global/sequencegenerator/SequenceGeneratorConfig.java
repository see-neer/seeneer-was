package com.repill.was.global.sequencegenerator;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SequenceGeneratorConfig {

    private static final int MACHINE_SEQUENCE_BIT_SIZE = 7;

    @Bean
    public MachineSequenceAllocator machineSequenceAllocator() {
        return new JdbcMachineSequenceAllocator(MACHINE_SEQUENCE_BIT_SIZE);
    }

    @Bean
    public SequenceGenerator sequenceGenerator(MachineSequenceAllocator allocator) {
        int machineSequence = allocator.allocate();

        return new SequenceGeneratorImpl(machineSequence, MACHINE_SEQUENCE_BIT_SIZE);
    }
}

