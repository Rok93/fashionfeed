package com.roki.fashionfeed.domain.share;

import com.roki.fashionfeed.domain.feed.Feed;
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

    @OneToOne(mappedBy = "share", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Feed feed;

    @Enumerated(EnumType.STRING)
    private ShareStatus shareStatus;

    @Builder
    public Share(ShareStatus shareStatus) {
        this.shareStatus = shareStatus;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
