package com.student.server.service.introduction;

import com.student.server.data.dto.IntroductionDTO;
import com.student.server.data.request.IntroductionRequest;
import com.student.server.data.request.PageableRequest;
import com.student.server.data.response.IntroductionResponse;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface IIntroductionService {

    Single<IntroductionDTO> createIntroduction(IntroductionRequest introductionRequest);

    Single<IntroductionResponse> deleteIntro(String id);

    Single<IntroductionResponse> selected();

    Single<IntroductionResponse> onSelected(String id);

    Single<List<IntroductionResponse>> getAllIntroduction();

    Single<List<IntroductionResponse>> getAllIntroduction(PageableRequest request);

}
