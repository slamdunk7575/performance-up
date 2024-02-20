package me.yanggang.performance.service;

import jakarta.servlet.http.HttpServletRequest;
import me.yanggang.performance.domain.Notice;
import me.yanggang.performance.dto.NoticeDto;
import me.yanggang.performance.repository.NoticeCustomRepository;
import me.yanggang.performance.repository.NoticeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeCustomRepository noticeCustomRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository,
                             NoticeCustomRepository noticeCustomRepository) {
        this.noticeRepository = noticeRepository;
        this.noticeCustomRepository = noticeCustomRepository;
    }

    @Override
    public List<NoticeDto> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();

        List<NoticeDto> noticeDtoList = notices.stream()
                .map(doc -> NoticeDto.builder().build())
                .collect(Collectors.toList());

        return noticeDtoList;
    }

    @Override
    public List<NoticeDto> findNoticesByPage(HttpServletRequest request, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        List<NoticeDto> noticesByPage = noticeCustomRepository.findNoticesByPage(pageable);
        return noticesByPage;
    }

    @Override
    public List<NoticeDto> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<NoticeDto> noticesByDates = noticeCustomRepository.findNoticesByDates(startDate, endDate);
        return noticesByDates;
    }

}
