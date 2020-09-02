package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.feed.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 지금 프로젝트에서는 필요한 부분은 아니지만
 * 확장성을 고려해서 (admin 계정의 경우 피드를 추가할 수 있어야 할 것이다!)
 */
@Getter
@Setter
@NoArgsConstructor
public class FeedSaveRequestDto implements Serializable {
    private String feedTitle;
    private String feedContent;
    private String feedImage;

    @Builder
    public FeedSaveRequestDto(String feedTitle, String feedContent, String feedImage) {
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
        this.feedImage = feedImage;
    }

    public Feed toEntity() {
        return Feed.builder()
                .feedTitle(feedTitle)
                .feedContent(feedContent)
                .feedImage(feedImage)
                .build();
    }
}
