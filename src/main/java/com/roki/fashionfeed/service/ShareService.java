package com.roki.fashionfeed.service;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import com.roki.fashionfeed.domain.share.Share;
import com.roki.fashionfeed.domain.share.ShareRepository;
import com.roki.fashionfeed.web.dto.ShareSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShareService {
    private final ShareRepository shareRepository;
    private final FeedRepository feedRepository;

    @Transactional
    public Long save(Long feedId, ShareSaveRequestDto shareSaveRequestDto) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드입니다. id=" + feedId));
        Share shareToFeed = shareSaveRequestDto.toEntity();
        shareToFeed.setFeed(feed);

        shareRepository.save(shareToFeed);
        return shareToFeed.getId();
    }

    @Transactional
    public void delete(Long feedId, Long userId) {
        shareRepository.deleteByUserIdAndFeed_Id(userId, feedId);
    }
}
