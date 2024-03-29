package me.yanggang.performance.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class NoticeDto {

    private String title;
    private String content;
    private String writer;

    @Builder
    public NoticeDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
