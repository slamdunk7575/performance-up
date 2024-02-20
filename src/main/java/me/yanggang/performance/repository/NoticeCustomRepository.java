package me.yanggang.performance.repository;

import me.yanggang.performance.dto.NoticeDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeCustomRepository {

    List<NoticeDto> findNoticesByPage(Pageable pageable);

    List<NoticeDto> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate);
}
