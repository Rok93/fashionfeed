package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.likefull.LikeType;
import com.roki.fashionfeed.domain.likefull.Likefull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LikefullSaveRequestDto {
    private Long userId;
    private LikeType likeType = LikeType.LIKE;

    @Builder
    public LikefullSaveRequestDto(Long userId) {
        this.userId = userId;
    }


    public Likefull toEntity() {
        return Likefull.builder()
                .userId(userId)
                .likeType(likeType)
                .build();
    }
}
