package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.share.Share;
import com.roki.fashionfeed.domain.share.ShareStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShareResponseDto {
    private Long id;
    private Long userId;
    private Long feedId;
    private ShareStatus shareStatus;

    public ShareResponseDto(Share entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.feedId = entity.getFeed().getId();
        this.shareStatus = entity.getShareStatus();
    }
}
