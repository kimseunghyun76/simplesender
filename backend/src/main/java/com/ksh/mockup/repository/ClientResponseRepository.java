package com.ksh.mockup.repository;

import com.ksh.mockup.entity.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ClientResponseRepository extends PagingAndSortingRepository<ClientResponse, Long> {

    Page<ClientResponse> findByDstMrnContainingOrSrcMrnContaining(String dstMrn,String srcMrn,Pageable pageable);
}