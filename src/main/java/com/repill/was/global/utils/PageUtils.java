package com.repill.was.global.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public final class PageUtils {

    public static  <T> Page<T> makePage(List<T> recentlyViewedItemResponses, String sort, int size, int page) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort).descending());
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), recentlyViewedItemResponses.size());
        return new PageImpl<>(recentlyViewedItemResponses.subList(start, end), pageRequest, recentlyViewedItemResponses.size());
    }
}
