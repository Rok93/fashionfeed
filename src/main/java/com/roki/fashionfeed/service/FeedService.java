package com.roki.fashionfeed.service;

import com.roki.fashionfeed.domain.feed.Feed;
import com.roki.fashionfeed.domain.feed.FeedRepository;
import com.roki.fashionfeed.web.dto.FeedResponseDto;
import com.roki.fashionfeed.web.dto.FeedSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;

    // 패션피드 상세페이지에서 사용
    @Transactional(readOnly = true)
    public FeedResponseDto findById(Long id) {
        Feed entity = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));
        return new FeedResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<FeedResponseDto> findAll(Pageable pageable) {
        return FeedResponseDto.of(feedRepository.findAll(pageable));
    }

    @Transactional
    public Feed save(FeedSaveRequestDto requestDto) {
        return feedRepository.save(requestDto.toEntity());
    }

    // 실제 테스트를 위한 Feed 20개 저장(test용도)
    @Transactional
    public void saveTestFeeds() {

        Feed feed1 = Feed.builder()
                .feedTitle("런던부터 파리 그리고 삐띠 워모까지.")
                .feedContent(" " +
                        "헤비웨이트 부츠, 하네스, 그랜대드 카디건, 텀블러 케이스 등 이번 2020년 가을·겨울 남성 패션위크를 꾸며낸 여러가지 아이템이 스타일링의 한 부분이 되어 무대에 올랐습니다. 런던을 시작으로 파리에서 마무리되는 메인 패션위크부터 피렌체에서 매 시즌 개최되는 남성복 박람회인 '삐띠 이매진 워모'의 게스트 디자이너로 참여한 브랜드까지.\n" +
                        "　\n")
                .feedImage("../images/feed" + 1 + ".jpg")
                .build();
        feedRepository.save(feed1);

        Feed feed2 = Feed.builder()
                .feedTitle("향수 및 화장품 부서의 모든 생산을 멈추고 손 세정제를 제작하는 LVMH.")
                .feedContent(" " +
                        "LVMH 그룹은 코로나 바이러스의 확산을 막기 위해 향수와 화장품 부서의 모든 생산 시설을 중단하고 손 세정제 제조에 힘을 쏟을 것이라는 소식을 전했습니다. LVMH 그룹의 회장 베르나르 아르노는 이 시스템으로 만들어진 손 세정제를 프랑스 보건 당국에 무료로 제공할 것이라는 소식도 함께 전했습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 2 + ".jpg")
                .build();
        feedRepository.save(feed2);

        Feed feed3 = Feed.builder()
                .feedTitle("미즈노 웨이브 라이더 10 모델에 담긴 우드 우드만의 컬러 웨이.")
                .feedContent(" " +
                        "지난 1월, 코펜하겐 패션위크에서 공개된 우드 우드의 2020년 가을·겨울 컬렉션에서 처음으로 모습을 드러낸 미즈노와의 협업 스니커즈가 발매를 앞두고 있습니다. 2007년 미즈노에서 처음 출시된 '웨이브 라이더 10' 모델을 베이스로 그레이 컬러를 메인으로 우드 우드만의 미니멀한 감성을 느껴볼 수 있습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 3 + ".jpg")
                .build();
        feedRepository.save(feed3);

        Feed feed4 = Feed.builder()
                .feedTitle("스포츠 무드를 만난 고딕 패션 챔피언과 릭 오웬스의 협업.")
                .feedContent(" " +
                        "릭 오웬스의 2020년 봄·여름 컬렉션 런웨이에서 처음 모습을 공개했던 챔피언과의 협업 프로젝트가 발매를 앞두고 있습니다. 시원한 메쉬 소재를 사용한 반바지를 비롯해 바디 수트 등 릭 오웬스 특유의 실루엣이 가미된 챔피언의 스포츠 웨어는 새로운 '스타 로고'와 함께 어우러져 있습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 4 + ".jpg")
                .build();
        feedRepository.save(feed4);

        Feed feed5 = Feed.builder()
                .feedTitle("2020 LVMH 프라이즈의 최종 후보가 발표되었습니다.")
                .feedContent(" " +
                        "'젊은 패션 디자이너'의 육성 및 지원을 목적으로 한 패션 콘테스트인 LVMH 프라이즈의 2020년을 장식하게 될 최종 후보 8인이 발표되었습니다. 이번 결승전에 진출한 8명의 디자이너들은 베테랑 패션 업계 관계자들과 LVMH 그룹의 산하 브랜드 디자이너로 구성된 68명의 심사위원들의 선택에 의해 선출되었습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 5 + ".jpg")
                .build();
        feedRepository.save(feed5);

        Feed feed6 = Feed.builder()
                .feedTitle("2020년 가을·겨울 패션위크 무대를 가득 채운 50개의 핸드백들.1")
                .feedContent(" " +
                        "봄·여름 컬렉션을 통해 여성복 브랜드들이 선보이는 핸드백은 대게 밝은 색상을 사용합니다. 그러나 가을·겨울 컬렉션에서는 보다 중립적인 색상을 보여주는 경우가 많죠. 이번 2020년 가을·겨울 컬렉션도 예외는 아니었습니다.\n" +
                        "　\n" +
                        "　\n")
                .feedImage("../images/feed" + 6 + ".jpg")
                .build();
        feedRepository.save(feed6);

        Feed feed7 = Feed.builder()
                .feedTitle("스포츠와 데님의 멋진 만남 뉴발란스에 리바이스 한 스푼.3")
                .feedContent(" " +
                        "미국을 대표하는 브랜드인 뉴발란스와 리바이스가 힘을 합쳤습니다. 뉴발란스는 'M1300' 시리즈의 35주년을 기념해 리바이스와 협업해 'M1300CL' 모델을 탄생시켰고 오는 3월 26일 발매를 예정에 두고 있으며, 높은 퀄리티를 자랑하는 뉴발란스의 'MADE IN USA' 모델이 리바이스와 협업하는 건 이번이 처음입니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 7 + ".jpg")
                .build();
        feedRepository.save(feed7);

        Feed feed8 = Feed.builder()
                .feedTitle("베이프의 설립자 니고(NIGO)와 버질 아블로의 루이비통 컬렉션.")
                .feedContent(" " +
                        "지난해 12월, 버질 아블로의 루이비통과 베이프의 설립자로 잘 알려져 있는 니고(NIGO)의 협업이 출시된다는 소식이 흘러나왔고 드디어 오늘 이 둘의 협업 프로젝트인 \"LV2\" 컬렉션의 일부분이 공개되었습니다. 체크무늬를 패치워크로 표현한 아이템과 두 남자의 시그니처를 자연스럽게 녹여낸 가죽 소재의 액세서리가 이번 컬렉션의 메인을 맡았으며 니고가 쌓아온 헤리티지 또한 가득 담겨 있었습니다.\n" +
                        "　" +
                        "　\n")
                .feedImage("../images/feed" + 8 + ".jpg")
                .build();
        feedRepository.save(feed8);

        Feed feed9 = Feed.builder()
                .feedTitle("파리 20 F/W 패션위크 베스트 컬렉션 TOP 3.")
                .feedContent(" " +
                        "LOUIS VUITTON\n" +
                        "　\n" +
                        "패션에 있어 과거로의 회귀는 분명 빠질 수 없는 부분입니다. 모든 디자이너가 그렇다고도 말할 수 있을 정도이죠. 그러나 루이비통 여성복의 아티스틱 디렉터를 맡고 있는 니콜라 제스키에르는 그 과거에 대해 더욱 자세히 연구하는 디자이너 중 한 명입니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 9 + ".jpg")
                .build();
        feedRepository.save(feed9);

        Feed feed10 = Feed.builder()
                .feedTitle("은퇴를 선언한 장 폴 고티에 매 시즌마다 디자이너들을 초대하는 프로젝트를 시작하다.")
                .feedContent(" " +
                        "은퇴를 선언한 장 폴 고티에 매 시즌마다\n" +
                        "디자이너들을 초대하는 프로젝트를 시작하다.\n" +
                        "　\n" +
                        "이번 2020년 봄·여름 쿠튀르 컬렉션을 끝으로 은퇴를 선언했던 장 폴 고티에가 새로운 프로젝트를 시작합니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 10 + ".jpg")
                .build();
        feedRepository.save(feed10);

        Feed feed11 = Feed.builder()
                .feedTitle("\"BOND, JAMES BOND\" 007의 의상을 제작한 톰 포드.0")
                .feedContent(" " +
                        "\"BOND, JAMES BOND\"\n" +
                        "007의 의상을 제작한 톰 포드.\n" +
                        "　\n" +
                        "4월 8일 국내 개봉을 앞두고 있는 '007 : 노 타임 투 다이'의 주인공, 제임스 본드 역을 맡은 다니엘 크레이그가 '톰 포드'가 직접 제작한 의상을 입습니다.\n" +
                        "　" +
                        "　\n")
                .feedImage("../images/feed" + 11 + ".jpg")
                .build();
        feedRepository.save(feed11);

        Feed feed12 = Feed.builder()
                .feedTitle("세계가 주목하는 런던의 디자이너 크레이그 그린과 아디다스의 협업.")
                .feedContent(" " +
                        "세계가 주목하는 런던의 디자이너\n" +
                        "크레이그 그린과 아디다스의 협업.\n" +
                        "　\n" +
                        "아디다스는 제가 항상 함께 일하기를 꿈꿔왔던 브랜드입니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 12 + ".jpg")
                .build();
        feedRepository.save(feed12);

        Feed feed13 = Feed.builder()
                .feedTitle("90년대 크루즈 룩의 부흥을 다시 알리는 더블탭스와 헬리 한센.")
                .feedContent(" " +
                        "90년대 크루즈 룩의 부흥을 다시\n" +
                        "알리는 더블탭스와 헬리 한센.\n" +
                        "　\n" +
                        "노르웨이 베이스의 마린 웨어 브랜드 헬리 한센(Helly Hansen)과 테츠 니시야마의 더블탭스(WTAPS)가 이번 2020년 봄·여름 시즌에 다시 한번 힘을 합쳤습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 13 + ".jpg")
                .build();
        feedRepository.save(feed13);

        Feed feed14 = Feed.builder()
                .feedTitle("관객 없이 쇼를 진행한 하이크(HYKE) 아디다스와의 협업 프로젝트까지.")
                .feedContent(" " +
                        "관객 없이 쇼를 진행한 하이크(HYKE)\n" +
                        "아디다스와의 협업 프로젝트까지.\n" +
                        "　\n" +
                        "히데아키 요시하라와 유키코 오데가 다루는 하이크가 지난 17일, 2020년 가을·겨울 컬렉션을 공개했습니다.\n" +
                        "　\n" +
                        "　\n")
                .feedImage("../images/feed" + 14 + ".jpg")
                .build();
        feedRepository.save(feed14);

        Feed feed15 = Feed.builder()
                .feedTitle("세계적인 포토그래퍼 위르겐 텔러와 함께 한 오프 화이트 아이웨어 & 백.")
                .feedContent(" " +
                        "세계적인 포토그래퍼 위르겐 텔러와\n" +
                        "함께 한 오프 화이트 아이웨어 & 백.\n" +
                        "　\n" +
                        "버질 아블로가 전개하는 오프 화이트가 세계적인 패션 포토그래퍼 위르겐 텔러와 만나 2020년 봄·여름 시즌 아이웨어 & 백의 캠페인 촬영을 진행했습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 15 + ".jpg")
                .build();
        feedRepository.save(feed15);

        Feed feed16 = Feed.builder()
                .feedTitle("라이카의 렌즈를 통해 세상을 보다.")
                .feedContent(" " +
                        "라이카의 렌즈를 통해 세상을 보다.\n" +
                        "　\n" +
                        "독일에서 시작된 아이웨어 브랜드 마이키타(MYKITA)가 카메라 브랜드 라이카와 손을 잡고 광학급 렌즈를 도입한 선글라스 컬렉션을 선보였습니다. \n" +
                        "　\n")
                .feedImage("../images/feed" + 16 + ".jpg")
                .build();
        feedRepository.save(feed16);

        Feed feed17 = Feed.builder()
                .feedTitle("프랑스 파리에 나타난 칸예 웨스트 그 누구도 예상하지 못한 그의 등장.")
                .feedContent(" " +
                        "프랑스 파리에 나타난 칸예 웨스트\n" +
                        "그 누구도 예상하지 못한 그의 등장.\n" +
                        "　\n" +
                        "'파리 패션위크'의 일정이 계속되는 가운데, 스케줄에 이름을 올리지 않았던 칸예 웨스트가 갑자기 등장했습니다. 그가 패션위크의 관객으로서 방문을 할 줄은 알았지만 아티스트이자 디자이너로 참여할 줄은 그 누구도 예상하지 못했죠.\n" +
                        "　\n" +
                        "　\n")
                .feedImage("../images/feed" + 17 + ".jpg")
                .build();
        feedRepository.save(feed17);

        Feed feed18 = Feed.builder()
                .feedTitle("도쿄와 파리에 이어 LA에서 프레젠테이션을 연 비즈빔.")
                .feedContent(" " +
                        "도쿄와 파리에 이어 LA에서\n" +
                        "프레젠테이션을 연 비즈빔.\n" +
                        "　\n" +
                        "최근 2020년 봄·여름 컬렉션을 공개한 나카무라 히로키의 비즈빔(visvim)이 로스앤젤레스에 위치한 플래그십 스토어에서 2020년 가을·겨울 컬렉션의 아이템을 전시회 형식으로 공개했습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 18 + ".jpg")
                .build();
        feedRepository.save(feed18);

        Feed feed19 = Feed.builder()
                .feedTitle("보틀 케이스로 패션위크를 물들인 겐조와 마린 세레 그리고 크리스찬 디올.")
                .feedContent(" " +
                        "보틀 케이스로 패션위크를 물들인\n" +
                        "겐조와 마린 세레 그리고 크리스찬 디올.\n" +
                        "　\n" +
                        "현재 열리고 있는 2020년 가을·겨울 파리 패션위크에서 겐조와 마린 세레 그리고 크리스찬 디올 등 많은 브랜드들이 텀블러를 휴대할 수 있는 보틀 케이스 아이템을 발표하고 있습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 19 + ".jpg")
                .build();
        feedRepository.save(feed19);

        Feed feed20 = Feed.builder()
                .feedTitle("리복의 퓨리 스니커즈와 합쳐진 메종 마르지엘라의 타비 부츠.")
                .feedContent(" " +
                        "리복의 퓨리 스니커즈와 합쳐진\n" +
                        "메종 마르지엘라의 타비 부츠.\n" +
                        "　\n" +
                        "지난 1월 프랑스 파리에서 공개된 메종 마르지엘라의 2020년 봄·여름 쿠튀르 컬렉션에서 처음 공개된 리복과의 협업 스니커즈가 어제 열린 기성복 컬렉션에서도 모습을 드러냈습니다.\n" +
                        "　\n")
                .feedImage("../images/feed" + 20 + ".jpg")
                .build();
        feedRepository.save(feed20);

    }
}
