package com.repill.was.global.sequencegenerator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

public class JdbcMachineSequenceAllocator implements MachineSequenceAllocator {

    private final int machineSequenceBitSize;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    public JdbcMachineSequenceAllocator(int machineSequenceBitSize) {
        this.machineSequenceBitSize = machineSequenceBitSize;
    }

    @Override
    public int allocate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        return transactionTemplate.execute(transactionStatus -> {
            String update = "UPDATE machine_sequence SET sequence = sequence + 1";
            jdbcTemplate.update(update);

            String select = "SELECT sequence FROM machine_sequence";

            Integer nextId;

            try {
                nextId = jdbcTemplate.queryForObject(select, Integer.class);
            } catch (EmptyResultDataAccessException e) {
                jdbcTemplate.update("INSERT INTO machine_sequence VALUES (1)");

                nextId = 1;
            }

            int sequenceModular = (int) Math.pow(2, machineSequenceBitSize);

            return nextId % sequenceModular;
        });
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
