package com.roki.fashionfeed.domain.feed;

import com.roki.fashionfeed.domain.BaseTimeEntity;
import com.roki.fashionfeed.domain.chat.Chat;
import com.roki.fashionfeed.domain.likefull.Likefull;
import com.roki.fashionfeed.domain.share.Share;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Feed extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String feedTitle;

    @Column(length = 255, nullable = false)
    private String feedContent;

    @Column(nullable = false)
    private String feedImage;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chats = new ArrayList<>();

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likefull> likefulls = new ArrayList<>(); //좋아요 개수를 파악하는 방법 Linkfull 모두 조회해서 카운트하기?

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Share> shares = new ArrayList<>();

    @Builder
    public Feed(String feedTitle, String feedContent, String feedImage) {
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
        this.feedImage = feedImage;
    }

}

