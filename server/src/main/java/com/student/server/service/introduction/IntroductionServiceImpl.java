package com.student.server.service.introduction;

import com.student.server.data.dto.IntroductionDTO;
import com.student.server.data.mappers.IntroductionMapper;
import com.student.server.data.request.IntroductionRequest;
import com.student.server.data.request.PageableRequest;
import com.student.server.data.response.IntroductionResponse;
import com.student.server.repositories.introduction.IntroductionRepository;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntroductionServiceImpl implements IIntroductionService {

    private final IntroductionRepository introductionRepository;

    @Override
    public Single<IntroductionDTO> createIntroduction(IntroductionRequest introductionRequest) {
        IntroductionDTO dto = IntroductionMapper.INSTANCE.toIntroductionDTO(introductionRequest);
        return introductionRepository.createWithSingle(dto);
    }

    @Override
    public Single<IntroductionResponse> deleteIntro(String id) {
        return introductionRepository.deleteIntro(id);
    }

    @Override
    public Single<IntroductionResponse> selected() {
        return introductionRepository.selected();
    }

    @Override
    public Single<IntroductionResponse> onSelected(String id) {
        return introductionRepository.onSelected(id);
    }

    @Override
    public Single<List<IntroductionResponse>> getAllIntroduction() {
        return introductionRepository.getAllIntroductions();
    }

    @Override
    public Single<List<IntroductionResponse>> getAllIntroduction(PageableRequest request) {
        return introductionRepository.getAllIntroductions(request);
    }

}
