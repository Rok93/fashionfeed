package com.roki.fashionfeed.domain.share;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.likefull.LikeType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Enumerated(EnumType.STRING)
    private ShareStatus shareStatus;

    @Builder
    public Share(Long userId, ShareStatus shareStatus) {
        this.userId = userId;
        this.shareStatus = shareStatus;
    }

    public void setFeed(Feed feed) {
        if (this.feed != null) {
            this.feed.getShares().remove(this);
        }
        this.feed = feed;
        feed.getShares().add(this);
    }

    public void changeShareStatus(ShareStatus shareStatus) {
        this.shareStatus = shareStatus;
    }
}
