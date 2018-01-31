package com.ksh.mockup.repository;

import com.ksh.mockup.entity.ClientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.Date;


public interface ClientResponseRepository extends PagingAndSortingRepository<ClientResponse, Long> {

    // dstMrn 이나 srcMrn 을 동시에 비교하는 것은 findby로는 어려워 보인다.(내가 못찾는건지)
    // 아니면 @Query 을 사용해서 진행해야 하는데, 내가 바라는 건 아니다.
    Page<ClientResponse> findByRegDateBetweenAndDstMrnContaining(LocalDateTime start_date, LocalDateTime  end_date,String dstMrn, Pageable pageable);
    Page<ClientResponse> findByRegDateBetweenAndSrcMrnContaining(LocalDateTime start_date, LocalDateTime  end_date,String srcMrn, Pageable pageable);
    Page<ClientResponse> findByRegDateBetween(LocalDateTime start_date, LocalDateTime  end_date, Pageable pageable);


}