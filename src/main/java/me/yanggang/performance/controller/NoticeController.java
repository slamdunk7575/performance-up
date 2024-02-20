package me.yanggang.performance.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.yanggang.performance.dto.NoticeDto;
import me.yanggang.performance.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("")
    public ResponseEntity<Object> findNoticesAll() {
        List<NoticeDto> notices = noticeService.getAllNotices();
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity<Object> findByPage(HttpServletRequest request, @PathVariable("page") Integer page) {
        List<NoticeDto> notices = noticeService.findNoticesByPage(request, page);
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

    @GetMapping("/dates")
    public ResponseEntity<Object> findNoticesByDates(@RequestParam("startDate") String startDate,
                                                     @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<NoticeDto> notices = noticeService.findNoticesByDates(
                LocalDateTime.parse(startDate, formatter),
                LocalDateTime.parse(endDate, formatter)
        );
        return new ResponseEntity<>(notices, HttpStatus.OK);
    }

}
