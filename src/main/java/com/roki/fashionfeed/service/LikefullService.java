package com.roki.fashionfeed.service;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import com.roki.fashionfeed.domain.likefull.Likefull;
import com.roki.fashionfeed.domain.likefull.LikefullRepository;
import com.roki.fashionfeed.web.dto.LikefullSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikefullService { //todo: share랑 like 똑같은 방식으로 생성하고 지우기 방식이면 충분할 것 같다!
    private final FeedRepository feedRepository;
    private final LikefullRepository likefullRepository;

    @Transactional
    public Long save(Long feedId, LikefullSaveRequestDto requestDto) { // likefull id 뿐만 아니라, user id도 받아와야한다. (user에 따라 같은 게시글이더라도 likefull, share 상태가 다르다!)
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + feedId));
        Likefull likefullToFeed = requestDto.toEntity();
        likefullToFeed.setFeed(feed);

        likefullRepository.save(likefullToFeed);
        return likefullToFeed.getId();
    }

    @Transactional
    public void delete(Long feedId, Long userId) {
        likefullRepository.deleteByUserIdAndFeed_Id(userId, feedId);
    }
}
