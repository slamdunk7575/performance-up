package me.yanggang.performance.service;

import me.yanggang.performance.dto.NoticeDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NoticeServiceImplTest {

    @Autowired
    private NoticeService noticeService;


    @DisplayName("모든 게시글을 조회할 수 있다.")
    @Test
    void find_all_notices() {
        // given & when
        List<NoticeDto> allNotices = noticeService.getAllNotices();

        // then
        assertThat(allNotices.size()).isNotZero();
    }

    @DisplayName("게시글을 페이징 처리하여 조회할 수 있다.")
    @Test
    void find_notices_by_page() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();

        // when
        List<NoticeDto> noticesByPage = noticeService.findNoticesByPage(request, 0);

        // then
        assertThat(noticesByPage.size()).isNotZero();
    }

    @DisplayName("날짜 사이의 게시들을 조회할 수 있다.")
    // @Test
    void find_notices_by_dates() {
        // given
        // 예: 2024-02-20 06:08:48.000000
        LocalDateTime startDate = LocalDateTime.of(2024, 02, 20, 00, 00, 00);
        LocalDateTime endDate = LocalDateTime.of(2024, 02, 20, 06, 59, 59);

        // when
        List<NoticeDto> noticesByDates = noticeService.findNoticesByDates(startDate, endDate);

        // then
        assertThat(noticesByDates.size()).isNotZero();
    }

}
