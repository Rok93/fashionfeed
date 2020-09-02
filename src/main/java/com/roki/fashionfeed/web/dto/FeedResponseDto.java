package com.roki.fashionfeed.web.dto;

import com.roki.fashionfeed.domain.feed.Feed;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FeedResponseDto {

    private Long id;
    private String feedTitle;
    private String feedContent;
    private String feedImage;
    private List<ChatResponseDto> chats;
    private List<LikefullResponseDto> likefulls;
    private List<ShareResponseDto> shares;
    private String createdDate;
    private LocalDateTime modifiedDate;

    public FeedResponseDto(Feed entity) {
        this.id = entity.getId();
        this.feedTitle = entity.getFeedTitle();
        this.feedContent = entity.getFeedContent();
        this.feedImage = entity.getFeedImage();

        this.chats = entity.getChats().stream()
                .map(ChatResponseDto::new)
                .collect(Collectors.toList());

        this.likefulls = entity.getLikefulls().stream()
                .map(LikefullResponseDto::new)
                .collect(Collectors.toList());

        this.shares = entity.getShares().stream()
                .map(ShareResponseDto::new)
                .collect(Collectors.toList());

        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate = entity.getModifiedDate();
    }

    public FeedResponseDto(Long id, String feedTitle, String feedContent, String feedImage) {
        this.id = id;
        this.feedTitle = feedTitle;
        this.feedContent = feedContent;
        this.feedImage = feedImage;
    }

    public static FeedResponseDto of(Feed feed) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(feed, FeedResponseDto.class);
    }

    public static Page<FeedResponseDto> of(Page<Feed> sourcePage) {
        return sourcePage.map(FeedResponseDto::of);
    }
}
