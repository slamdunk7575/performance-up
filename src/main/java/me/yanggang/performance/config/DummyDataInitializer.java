package me.yanggang.performance.config;

import me.yanggang.performance.domain.Notice;
import me.yanggang.performance.repository.NoticeRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class DummyDataInitializer implements ApplicationRunner {

    private final NoticeRepository noticeRepository;

    public DummyDataInitializer(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 게시글 생성 (생성일: 2024-02-19): 100건
        for (int i = 1; i <= 100; i++) {
            Notice notice = Notice.builder()
                    .title("테스트 게시글_" + i)
                    .content("테스트 블라블라_" + i)
                    .writer("slamdunk7575")
                    .createDate(LocalDateTime.of(2024, 02, 19, 11, 11, 11))
                    .build();
            noticeRepository.save(notice);
        }

        // 게시글 생성 (오늘): 900건
        for (int i = 101; i <= 1000; i++) {
            Notice notice = Notice.builder()
                    .title("테스트 게시글_" + i)
                    .content("테스트 블라블라_" + i)
                    .writer("slamdunk7575")
                    .build();
            noticeRepository.save(notice);
        }

    }
}
