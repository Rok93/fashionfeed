package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.likefull.LikeType;
import com.roki.fashionfeed.domain.likefull.Likefull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LikefullResponseDto {
    private Long id;
    private Long userId;
    private Long feedId;
    private LikeType likeType;

    public LikefullResponseDto(Likefull entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.feedId = entity.getFeed().getId();
        this.likeType = entity.getLikeType();
    }
}
