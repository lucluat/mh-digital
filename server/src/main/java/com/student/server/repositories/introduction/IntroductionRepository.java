package com.student.server.repositories.introduction;

import com.server.model.tables.records.IntroductionRecord;
import com.student.server.config.exception.RestApiException;
import com.student.server.data.dto.IntroductionDTO;
import com.student.server.data.mappers.IntroductionMapper;
import com.student.server.data.request.PageableRequest;
import com.student.server.data.response.IntroductionResponse;
import com.student.server.repositories.AbstractCRUDRepository;
import io.reactivex.rxjava3.core.Single;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.server.model.tables.Introduction.INTRODUCTION;

@Repository
public class IntroductionRepository extends AbstractCRUDRepository<IntroductionRecord, IntroductionDTO> {

    public IntroductionRepository(DSLContext dsl) {
        super(dsl, INTRODUCTION, INTRODUCTION.ID, IntroductionDTO.class);
    }

    public Single<List<IntroductionResponse>> getAllIntroductions() {
        return Single.fromCallable(() -> dsl
                .selectFrom(INTRODUCTION)
                .where(INTRODUCTION.IS_DELETED.eq(false))
                .fetch(item -> {
                    IntroductionDTO dto = IntroductionMapper.INSTANCE.toIntroductionDTO(item);
                    return IntroductionMapper.INSTANCE.toResponse(dto);
                }));
    }

    public Single<IntroductionResponse> onSelected(String id) {
        IntroductionDTO dto = super.getById(id);
        if (dto == null || dto.getIsDeleted()) {
            throw new RestApiException("Introduction not found");
        }
        return Single.fromCallable(() -> {
            dsl.update(INTRODUCTION)
                    .set(INTRODUCTION.IS_SELECTED, false)
                    .where(INTRODUCTION.IS_SELECTED.eq(true));
            dsl.update(INTRODUCTION)
                    .set(INTRODUCTION.IS_SELECTED, true)
                    .where(INTRODUCTION.ID.eq(id));
            return IntroductionMapper.INSTANCE.toResponse(super.getById(id));
        });
    }

    public Single<List<IntroductionResponse>> getAllIntroductions(PageableRequest request) {
        return Single.fromCallable(() -> dsl
                .selectFrom(INTRODUCTION)
                .where(INTRODUCTION.IS_DELETED.eq(false))
                .limit(request.getSize())
                .offset(request.getPage() * request.getSize())
                .fetch(item -> {
                    IntroductionDTO dto = IntroductionMapper.INSTANCE.toIntroductionDTO(item);
                    return IntroductionMapper.INSTANCE.toResponse(dto);
                }));
    }

    public Single<IntroductionResponse> deleteIntro(String id) {
        IntroductionDTO dto = super.getById(id);
        if (dto == null) {
            throw new RestApiException("Introduction not found");
        }
        return Single.fromCallable(() -> {
            dsl.update(INTRODUCTION)
                    .set(INTRODUCTION.IS_DELETED, true)
                    .where(INTRODUCTION.ID.eq(id))
                    .execute();
            return IntroductionMapper.INSTANCE.toResponse(super.getById(id));
        });
    }

    public Single<IntroductionResponse> selected() {
        return Single.fromCallable(() -> {
            IntroductionDTO dto = dsl.selectFrom(INTRODUCTION)
                    .where(INTRODUCTION.IS_DELETED.eq(false).and(INTRODUCTION.IS_SELECTED.eq(true)))
                    .fetchOne(IntroductionMapper.INSTANCE::toIntroductionDTO);
            if (dto == null) {
                throw new RestApiException("Introduction not found");
            }
            return IntroductionMapper.INSTANCE.toResponse(dto);
        });
    }

}
