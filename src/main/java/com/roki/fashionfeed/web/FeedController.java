package com.roki.fashionfeed.web;

import com.roki.fashionfeed.config.auth.LoginUser;
import com.roki.fashionfeed.config.auth.dto.SessionUser;
import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.service.UserService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class FeedController {
    private final FeedService feedService;
    private final UserService userService;

    @GetMapping("/feeds/{id}")
    public String feedDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        FeedResponseDto findFeed = feedService.findById(id);
        model.addAttribute("feed", findFeed);

        if (user != null) {
            Long userId = userService.findUserIdByEmail(user.getEmail());
            boolean isLikeFeedByUser = findFeed.getLikefulls().stream()
                    .anyMatch(chat -> chat.getUserId().equals(userId));
            model.addAttribute("isLikeByUser", isLikeFeedByUser);

            boolean isShareFeedByUser = findFeed.getShares().stream()
                    .anyMatch(share -> share.getUserId().equals(userId));
            model.addAttribute("isShareByUser", isShareFeedByUser);
        }
        return "feed-detail";
    }
}
