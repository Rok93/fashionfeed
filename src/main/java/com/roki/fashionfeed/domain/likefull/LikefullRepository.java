package com.roki.fashionfeed.domain.likefull;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikefullRepository extends JpaRepository<Likefull, Long> {

    void deleteByUserIdAndFeed_Id(Long userId, Long feedId);
}
