package com.ksh.mockup.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class VuePageable extends PageRequest {

    public VuePageable(int page, int size, Sort sort) {
        super(page, size, sort);
    }
}
