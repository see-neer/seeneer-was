package com.repill.was.global.config;

import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Component
//@Slf4j
//@RequiredArgsConstructor
public class RedisCacheClient implements CacheClient {
//    private static final String DURATION_NULL_ERROR_MSG = "[RedisClientImpl] duration is null";
//    private final RedisTemplate redisTemplate;
//
//    //--------- Key ---------//
//
//    @Override
//    public void deleteByKey(String key) {
//        redisTemplate.delete(key);
//    }
//
//    @Override
//    public void expireByKey(String key, long timeout, TimeUnit timeUnit) {
//        redisTemplate.expire(key, timeout, timeUnit);
//    }
//
//    //--------- String ---------//
//
//    @Override
//    public <T> T findFromString(String key, Class<T> clazz) {
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        try {
//            return JsonRedisSerializer.convertValue(valueOperations.get(key), clazz);
//        } catch(Exception e) {
//            log.info(e.getMessage(), e);
//            return null;
//        }
//    }
//
//    @Override
//    public <T> T findFromString(String key, TypeReference<T> typeReference) {
//        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
//        try {
//            return JsonRedisSerializer.convertValue(valueOperations.get(key), typeReference);
//        } catch(Exception e) {
//            log.info(e.getMessage(), e);
//            return null;
//        }
//    }
//
//    @Override
//    public <T> List<T> findAllFromString(List<String> keys, Class<T> clazz) {
//        if (CollectionUtils.isEmpty(keys)) {
//            return Collections.emptyList();
//        }
//        List<Object> values = redisTemplate.opsForValue().multiGet(keys);
//        return values.stream().map(value -> JsonRedisSerializer.convertValue(value, clazz)).collect(Collectors.toList());
//    }
//
//    @Override
//    public <T> void saveValueOfString(String key, T value, Duration duration) {
//        if (Duration.ZERO.equals(duration)) {
//            redisTemplate.opsForValue().set(key, value);
//        } else {
//            redisTemplate.opsForValue().set(key, value, duration);
//        }
//    }
//
//    @Override
//    public <T> void saveValuesOfString(Map<String, T> keyValuePairs, Duration duration) {
//        keyValuePairs.keySet().forEach(key -> {
//            if (Duration.ZERO.equals(duration)) {
//                redisTemplate.opsForValue().set(key, keyValuePairs.get(key));
//            } else {
//                redisTemplate.opsForValue().set(key, keyValuePairs.get(key), duration);
//            }
//        });
//    }
//
//    @Override
//    public Long incrementValueOfString(String key, long increment) {
//        return redisTemplate.opsForValue().increment(key, increment);
//    }
//
//    @Override
//    public Long decrementValueOfString(String key, long decrement) {
//        return redisTemplate.opsForValue().decrement(key, decrement);
//    }
//
//    //--------- Hash ---------//
//
//    @Override
//    public <T> void setValueOfHashField(String key, String field, T value, Duration duration) {
//        Assert.notNull(duration, DURATION_NULL_ERROR_MSG);
//        redisTemplate.opsForHash().put(key, field, value);
//        if (!Duration.ZERO.equals(duration)) {
//            expireByKey(key, duration.getSeconds(), TimeUnit.SECONDS);
//        }
//    }
//
//    @Override
//    public void setValuesOfHashFields(String key, Map<String, String> values, Duration duration) {
//        Assert.notNull(duration, DURATION_NULL_ERROR_MSG);
//        redisTemplate.opsForHash().putAll(key, values);
//        if (!Duration.ZERO.equals(duration)) {
//            expireByKey(key, duration.getSeconds(), TimeUnit.SECONDS);
//        }
//    }
//
//    @Override
//    public <T> void incrementValueOfHashField(String key, T field, long increment) {
//        redisTemplate.opsForHash().increment(key, field, increment);
//    }
//
//    @Override
//    public <T> void decrementValueOfHashField(String key, T field, long decrement) {
//        redisTemplate.opsForHash().increment(key, field, decrement);
//    }
//
//    @Override
//    public <T> T findValueOfHashField(String key, String field, Class<T> clazz) {
//        HashOperations<String, String, Object> operations = redisTemplate.opsForHash();
//        Object value = operations.get(key, field);
//        return JsonRedisSerializer.convertValue(value, clazz);
//    }
//
//    @Override
//    public <T> Map<String, T> findAllFromHash(String key, Class<T> clazz) {
//        Map<String, Object> entries = redisTemplate.opsForHash().entries(key);
//        return entries.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> JsonRedisSerializer.convertValue(entry.getValue(), clazz)));
//    }
//
//    @Override
//    public <T> Map<String, Map<String, T>> findAllFromHash(List<String> keys, Class<T> clazz) {
//        List<Map<String, Object>> entries = redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
//            keys.forEach(key -> redisConnection.hGetAll(key.getBytes()));
//            return null;
//        });
//
//        return IntStream.range(0, keys.size()).boxed().collect(Collectors.toMap(
//                keys::get,
//                key -> entries.get(key).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> JsonRedisSerializer.convertValue(entry.getValue(), clazz)))
//        ));
//    }
//
//    public void removeFromHash(String key, String field) {
//        redisTemplate.opsForHash().delete(key, field);
//    }
//
//    public void removeFromHash(String key, List<String> field) {
//        redisTemplate.opsForHash().delete(key, field.toArray());
//    }
//
//    @Override
//    public <T> Set<T> findFromSet(String key, Class<T> clazz) {
//        Set<String> set = redisTemplate.boundSetOps(key).members();
//
//        if(set == null){
//            return new LinkedHashSet<>();
//        }
//        return set.stream().map(map->JsonRedisSerializer.convertValue(map, clazz)).collect(Collectors.toCollection(LinkedHashSet::new));
//    }
//
//    @Override
//    public <T> void addSetValue(String key, Set<T> sets, Duration duration) {
//        Assert.notNull(duration, DURATION_NULL_ERROR_MSG);
//        sets.stream().forEach(value -> redisTemplate.opsForSet().add(key, value));
//        if (!Duration.ZERO.equals(duration)) {
//            expireByKey(key, duration.getSeconds(), TimeUnit.SECONDS);
//        }
//    }
//    //--------- Sorted Set ---------//
//
//    @Override
//    public <T> void incrementScoreInSortedSet(String key, T value, double increment) {
//        redisTemplate.opsForZSet().incrementScore(key, value, increment);
//    }
//
//    @Override
//    public <T> void decrementScoreInSortedSet(String key, T value, double decrement) {
//        redisTemplate.opsForZSet().incrementScore(key, value, decrement * -1);
//    }
//
//    @Override
//    public <T> List<Pair<T, Double>> findFromSortedSetByRange(String key, long start, long end) {
//        Set<ZSetOperations.TypedTuple<T>> results =  redisTemplate.opsForZSet().rangeWithScores(key, start, end);
//
//        if (results == null) {
//            return new ArrayList<>();
//        }
//
//        return results.stream()
//                .map(r -> Pair.of(r.getValue(), r.getScore()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public <T> List<Pair<T, Double>> findFromSortedSetByRange(String key, long start, long end, Class<T> clazz) {
//        Set<ZSetOperations.TypedTuple<T>> results =  redisTemplate.opsForZSet().rangeWithScores(key, start, end);
//
//        if (results == null) {
//            return new ArrayList<>();
//        }
//
//        return results.stream()
//                .map(r -> Pair.of(JsonRedisSerializer.convertValue(r.getValue(), clazz), r.getScore()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public <T> List<Pair<T, Double>> findFromSortedSetByReverseRange(String key, long start, long end) {
//        Set<ZSetOperations.TypedTuple<T>> results = redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
//
//        if (results == null) {
//            return new ArrayList<>();
//        }
//
//        return results.stream()
//                .map(r -> Pair.of(r.getValue(), r.getScore()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public <T> void addSortedSetValueWithScore(String key, T value, double score, Duration duration) {
//        Assert.notNull(duration, DURATION_NULL_ERROR_MSG);
//        redisTemplate.opsForZSet().add(key, value, score);
//        if (!Duration.ZERO.equals(duration)) {
//            expireByKey(key, duration.getSeconds(), TimeUnit.SECONDS);
//        }
//    }
//
//    @Override
//    public <T> void addSortedSetValuesWithScore(String key, Set<Pair<T, Double>> tuples, Duration duration) {
//        Assert.notNull(duration, DURATION_NULL_ERROR_MSG);
//        Set<ZSetOperations.TypedTuple<T>> typedTuples = tuples.stream()
//                .map(pair -> new DefaultTypedTuple<>(pair.getLeft(), pair.getRight()))
//                .collect(Collectors.toSet());
//
//        redisTemplate.opsForZSet().add(key, typedTuples);
//        if (!Duration.ZERO.equals(duration)) {
//            expireByKey(key, duration.getSeconds(), TimeUnit.SECONDS);
//        }
//    }
//
//    @Override
//    public <T> void removeFromSortedSet(String key, T value) {
//        redisTemplate.opsForZSet().remove(key, value);
//    }
//
//    @Override
//    public <T> void removeFromSortedSet(String key, List<T> value) {
//        redisTemplate.opsForZSet().remove(key, value.toArray());
//    }
//
//    @Override
//    public void removeFromSortedSetRange(String key, long start, long end) {
//        redisTemplate.opsForZSet().removeRange(key, start, end);
//    }
//
//
//    @Override
//    public <T> Double findScoreFromSortedSet(String key, T value) {
//        return redisTemplate.opsForZSet().score(key, value);
//    }
//
//    @Override
//    public <T> List<Double> findScoreFromSortedSet(String key, List<T> values) {
//        return redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
//            values.forEach(value -> redisConnection.zScore(key.getBytes(), redisTemplate.getValueSerializer().serialize(value)));
//
//            return null;
//        });
//    }
//
//    @Override
//    public <T> Set<T> findValuesFromSortedSetScoresBetween(String key, double min, double max) {
//        return Optional.ofNullable(redisTemplate.opsForZSet().rangeByScore(key, min, max)).orElseGet(HashSet::new);
//    }
//
//    @Override
//    public <T> Long findRankFromSortedSet(String key, T value) {
//        return redisTemplate.opsForZSet().rank(key, value);
//    }
//
//    @Override
//    public <T> List<Long> findRanksFromSortedSet(String key, List<T> values) {
//        return redisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
//            values.forEach(value -> redisConnection.zRank(key.getBytes(), redisTemplate.getValueSerializer().serialize(value)));
//
//            return null;
//        });
//    }
//
//    @Override
//    public Long getCountFromSortedSetScoresBetween(String key, double min, double max) {
//        return redisTemplate.opsForZSet().count(key, min, max);
//    }
}

