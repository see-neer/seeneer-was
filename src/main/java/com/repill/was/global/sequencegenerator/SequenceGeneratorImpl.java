package com.repill.was.global.sequencegenerator;

public class SequenceGeneratorImpl implements SequenceGenerator {

    private static final int MAX_BIT_SIZE = 53;
    private static final long BASE_TIMESTAMP = 1546300800000L; // (GMT) 2019년 1월 1일 AM 12:00:00
    private static final int TIMESTAMP_BIT_SIZE = 41;
    private static final int TIMESTAMP_SHIFT = MAX_BIT_SIZE - TIMESTAMP_BIT_SIZE;

    private final int machineSequence;
    private final int machineSequenceShift;
    private final int sequenceMask;

    private long lastTimestamp;
    private int sequence;

    public SequenceGeneratorImpl(int machineSequence, int machineSequenceBitSize) {
        this.machineSequence = machineSequence;
        this.machineSequenceShift = MAX_BIT_SIZE - TIMESTAMP_BIT_SIZE - machineSequenceBitSize;

        int sequenceBitSize = MAX_BIT_SIZE - TIMESTAMP_BIT_SIZE - machineSequenceBitSize;
        this.sequenceMask = (int) Math.pow(2, sequenceBitSize) - 1;
        this.lastTimestamp = System.currentTimeMillis();
        this.sequence = 0;
    }

    @Override
    public synchronized Long generate() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new SequenceGeneratorTimestampException();
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;

            if (sequence == 0) {
                timestamp = waitNextTimestamp(lastTimestamp);
            }
        } else {
            sequence = 0;
        }

        lastTimestamp = timestamp;

        return ((timestamp - BASE_TIMESTAMP) << TIMESTAMP_SHIFT)
                | machineSequence << machineSequenceShift
                | sequence;
    }

    private long waitNextTimestamp(long lastTimestamp) {
        long current = System.currentTimeMillis();

        while (lastTimestamp >= current) {
            current = System.currentTimeMillis();
        }

        return lastTimestamp + 1;
    }
}

