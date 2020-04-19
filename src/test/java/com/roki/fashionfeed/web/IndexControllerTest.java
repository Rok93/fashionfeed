package com.roki.fashionfeed.web;

import com.roki.fashionfeed.domain.feed.FeedRepository;
import com.roki.fashionfeed.service.FeedService;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import com.roki.fashionfeed.web.dto.FeedSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FeedService feedService;

    @Autowired
    private FeedRepository feedRepository;

    @Before
    public void setUp() throws Exception {
        //given
        IntStream.rangeClosed(1, 5)
                .forEach(n -> feedService.save(FeedSaveRequestDto.builder()
                        .feedTitle("title" + n)
                        .feedContent("content" + n)
                        .feedImage("image" + n)
                        .build())
                );
    }

    @After
    public void tearDown() throws Exception {
        feedRepository.deleteAll();
    }

    @Test
    public void test_메인페이지_로딩() throws Exception {
        //when then
        MvcResult mvcResult = mvc.perform(get("/").accept(MediaType.TEXT_HTML))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index")) // view가 "index.mustache"인지 확인
                .andExpect(handler().handlerType(IndexController.class)) // 담당 컨트롤러 확인
                .andExpect(model().attributeExists("feedPage")) // feedPage model이 존재하는지 확인
                .andExpect(handler().methodName("index")) // 메서드 이름이 index인지 확인
                .andReturn();

        Map<String, Object> models = mvcResult
                .getModelAndView()
                .getModel();

        Page<FeedResponseDto> feedPage = (Page<FeedResponseDto>) models.get("feedPage");

        assertThat(feedPage.getSize()).isEqualTo(10);
        assertThat(feedPage.getTotalElements()).isEqualTo(5);
        assertThat(feedPage.getTotalPages()).isEqualTo(1);
        assertThat(feedPage.getContent().size()).isEqualTo(5);

    }
}