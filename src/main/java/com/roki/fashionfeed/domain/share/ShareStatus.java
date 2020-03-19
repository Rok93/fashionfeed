package com.roki.fashionfeed.domain.share;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShareStatus {
    SHARE(true), UNSHARE(false);

    private final boolean value;


}
