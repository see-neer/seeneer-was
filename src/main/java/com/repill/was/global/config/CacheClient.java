package com.repill.was.global.config;


import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.tuple.Pair;
import org.redisson.api.RLock;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface CacheClient {

    //--------- Key ---------//

    void deleteByKey(String key);

    void expireByKey(String key, long timeout, TimeUnit timeUnit);

    //--------- String ---------//

    <T> T findFromString(String key, Class<T> clazz);

    <T> T findFromString(String key, TypeReference<T> typeReference);

    <T> List<T> findAllFromString(List<String> keys, Class<T> clazz);

    <T> void saveValueOfString(String key, T value, Duration duration);

    <T> void saveValuesOfString(Map<String, T> keyValuePairs, Duration duration);

    Long incrementValueOfString(String key, long increment);

    Long decrementValueOfString(String key, long decrement);

    //--------- Hash ---------//

    <T> void setValueOfHashField(String key, String field, T value, Duration duration);

    void setValuesOfHashFields(String key, Map<String, String> values, Duration duration);

    <T> void incrementValueOfHashField(String key, T field, long increment);

    <T> void decrementValueOfHashField(String key, T field, long decrement);

    <T> T findValueOfHashField(String key, String field, Class<T> clazz);

    <T> Map<String, T> findAllFromHash(String key, Class<T> clazz);

    <T> Map<String, Map<String, T>> findAllFromHash(List<String> keys, Class<T> clazz);

    void removeFromHash(String key, String field);

    void removeFromHash(String key, List<String> field);

    //--------- Set ---------//

    <T> Set<T> findFromSet(String key, Class<T> clazz);

    <T> void addSetValue(String key, Set<T> sets, Duration duration);

    //--------- Sorted Set ---------//

    <T> void incrementScoreInSortedSet(String key, T value, double increment);

    <T> void decrementScoreInSortedSet(String key, T value, double increment);

    <T> List<Pair<T, Double>> findFromSortedSetByRange(String key, long start, long end);
    <T> List<Pair<T, Double>> findFromSortedSetByRange(String key, long start, long end, Class<T> clazz);

    <T> List<Pair<T, Double>> findFromSortedSetByReverseRange(String key, long start, long end);

    <T> Set<T> findValuesFromSortedSetScoresBetween(String key, double min, double max);

    <T> void addSortedSetValueWithScore(String key, T value, double score, Duration duration);

    <T> void addSortedSetValuesWithScore(String key, Set<Pair<T, Double>> tuples, Duration duration);

    <T> void removeFromSortedSet(String key, T value);

    <T> void removeFromSortedSet(String key, List<T> value);

    void removeFromSortedSetRange(String key, long start, long end);

    <T> Double findScoreFromSortedSet(String key, T value);

    <T> List<Double> findScoreFromSortedSet(String key, List<T> values);

    <T> Long findRankFromSortedSet(String key, T value);

    <T> List<Long> findRanksFromSortedSet(String key, List<T> values);

    Long getCountFromSortedSetScoresBetween(String key, double min, double max);
}
