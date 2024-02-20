package me.yanggang.performance.service;

import jakarta.servlet.http.HttpServletRequest;
import me.yanggang.performance.dto.NoticeDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {

    List<NoticeDto> getAllNotices();

    List<NoticeDto> findNoticesByPage(HttpServletRequest request, int pageNumber);

    List<NoticeDto> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate);
}
