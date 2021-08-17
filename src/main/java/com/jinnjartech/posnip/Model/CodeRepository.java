package com.jinnjartech.posnip.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    Code findByUuid(String uuid);

    List<Code> findTop10ByTimeLessThanEqualAndViewsLessThanEqualOrderByRealDateDesc(long time, int views);

    @Transactional
    void deleteByUuid(String uuid);
}
