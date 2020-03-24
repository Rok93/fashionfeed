package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.share.Share;
import com.roki.fashionfeed.domain.share.ShareStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ShareSaveRequestDto {
    private Long userId;
    private ShareStatus shareStatus = ShareStatus.SHARE;

    public ShareSaveRequestDto(Long userId) {
        this.userId = userId;
    }

    public Share toEntity() {
        return Share.builder()
                .userId(userId)
                .shareStatus(shareStatus)
                .build();
    }
}
