package me.yanggang.performance.repository;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import me.yanggang.performance.domain.QNotice;
import me.yanggang.performance.dto.NoticeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static me.yanggang.performance.domain.QNotice.notice;

@Repository
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public NoticeCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<NoticeDto> findNoticesByPage(Pageable pageable) {
        List<NoticeDto> noticeDtoList =
                jpaQueryFactory.select(Projections.fields(NoticeDto.class,
                        notice.title.as("title"),
                        notice.content.as("content"),
                        notice.writer.as("writer"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, {1})",
                                notice.createDate, ConstantImpl.create("%Y-%m-%d %H:%i:%S")).as("createDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, {1})",
                                notice.updateDate, ConstantImpl.create("%Y-%m-%d %H:%i:%S")).as("updateDate")
                ))
                .from(notice)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return noticeDtoList;
    }

    @Override
    public List<NoticeDto> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<NoticeDto> noticeDtoList = jpaQueryFactory.select(Projections.fields(NoticeDto.class,
                        notice.title.as("title"),
                        notice.content.as("content"),
                        notice.writer.as("writer"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, {1})",
                                notice.createDate, ConstantImpl.create("%Y-%m-%d %H:%i:%S")).as("createDate"),
                        Expressions.stringTemplate("DATE_FORMAT({0}, {1})",
                                notice.updateDate, ConstantImpl.create("%Y-%m-%d %H:%i:%S")).as("updateDate")
                ))
                .from(notice)
                .where(searchDateFilter(notice, startDate, endDate))
                .fetch();

        return noticeDtoList;
    }

    // 날짜 필터
    private BooleanExpression searchDateFilter(QNotice notice, LocalDateTime startDate, LocalDateTime endDate) {
        BooleanExpression isGoeStartDate = notice.createDate.goe(startDate);
        BooleanExpression isLoeEndDate = notice.createDate.loe(endDate);
        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }
}
