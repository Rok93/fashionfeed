package com.roki.fashionfeed.domain.share;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Long> {

    void deleteByUserIdAndFeed_Id(Long userId, Long feedId);
}
