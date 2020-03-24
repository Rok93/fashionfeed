package com.roki.fashionfeed.web;

import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class FeedController {
    private final FeedService feedService;

    @GetMapping("/feed/{id}")
    public String feedDetail(@PathVariable Long id, Model model, HttpServletRequest request) {
        FeedResponseDto findFeed = feedService.findById(id);
        model.addAttribute("feed", findFeed);
        HttpSession session = request.getSession();
        Object sessionValue = session.getAttribute("sessionUser");
        if (sessionValue != null) {
            Long userId = (long) (int) sessionValue;
            boolean isLikeFeedByUser = findFeed.getLikefulls().stream()
                    .anyMatch(chat -> chat.getUserId().equals(userId));
            model.addAttribute("isLikeByUser", isLikeFeedByUser);

            boolean isShareFeedByUser = findFeed.getShares().stream()
                    .anyMatch(share -> share.getUserId().equals(userId));
            model.addAttribute("isShareByUser", isShareFeedByUser);
        }
        return "feed_detail";
    }

}
