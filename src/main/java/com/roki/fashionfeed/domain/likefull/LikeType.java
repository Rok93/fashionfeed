package com.roki.fashionfeed.domain.likefull;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LikeType {
    LIKE(true), UNLIKE(false);

    private final boolean value;

}
