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

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    @Builder
    public Likefull(LikeType likeType, Long userId) {
        this.likeType = likeType;
        this.userId = userId;
    }

    public void setFeed(Feed feed) {
        if (this.feed != null) {
            this.feed.getLikefulls().remove(this);
        }
        this.feed = feed;
        feed.getLikefulls().add(this);
    }

    public void changeLikeType(LikeType likeType) {
        this.likeType = likeType;
    }

}
