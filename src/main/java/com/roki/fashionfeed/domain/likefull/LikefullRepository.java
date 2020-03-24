package com.roki.fashionfeed.domain.likefull;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikefullRepository extends JpaRepository<Likefull, Long> {

    Likefull findByUserIdAndFeed_Id(Long userId, Long feedId);

    void deleteByUserIdAndFeed_Id(Long userId, Long feedId);
}
