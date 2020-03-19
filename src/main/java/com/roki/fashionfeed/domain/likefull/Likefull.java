package com.roki.fashionfeed.domain.likefull;

import com.roki.fashionfeed.domain.feed.Feed;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Likefull {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "likefull")
    private Feed feed;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    @Builder
    public Likefull(LikeType likeType) {
        this.likeType = likeType;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
