package com.student.server.controller;

import com.student.server.data.constant.MappingConstant;
import com.student.server.data.mappers.IntroductionMapper;
import com.student.server.data.request.IntroductionRequest;
import com.student.server.data.request.PageableRequest;
import com.student.server.data.response.IntroductionResponse;
import com.student.server.data.response.ResponseObject;
import com.student.server.service.introduction.IIntroductionService;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MappingConstant.INTRODUCTION)
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
@Validated
public class IntroductionController {

    private final IIntroductionService introductionService;

    @GetMapping("/page")
    public Single<ResponseObject<List<IntroductionResponse>>> getAllIntroductions(PageableRequest pageableRequest) {
        return introductionService.getAllIntroduction(pageableRequest)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(res,
                        HttpStatus.OK,
                        "Get list introduction success"))
                .doOnError(e -> log.error("[ERROR] [GET-INTRODUCTIONS-PAGE] {}", e.getMessage()));
    }

    @GetMapping
    public Single<ResponseObject<List<IntroductionResponse>>> getAllIntroductions() {
        return introductionService.getAllIntroduction()
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(res,
                        HttpStatus.OK,
                        "Get list introduction success"))
                .doOnError(e -> log.error("[ERROR] [GET-INTRODUCTIONS] {}", e.getMessage()));
    }

    @PostMapping
    public Single<ResponseObject<IntroductionResponse>> create(@RequestBody @Valid IntroductionRequest request) {
        return introductionService.createIntroduction(request)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(
                        IntroductionMapper.INSTANCE.toResponse(res),
                        HttpStatus.OK,
                        "Create introduction successfully"
                ))
                .doOnError(e -> log.error("[ERROR] [ADD-INTRODUCTION] {}", e.getMessage()));
    }

    @DeleteMapping("/{id}")
    public Single<ResponseObject<IntroductionResponse>> delete(@PathVariable("id") String id) {
        return introductionService.deleteIntro(id)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(
                        res, HttpStatus.OK, "Delete introduction successfully"
                ))
                .doOnError(e -> log.error("[ERROR] [DELETE-INTRODUCTION] {}", e.getMessage()));
    }

    @GetMapping("/selected")
    public Single<ResponseObject<IntroductionResponse>> getSelectedIntroductions() {
        return introductionService.selected()
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(
                        res, HttpStatus.OK, "Get selected introduction successfully"
                ))
                .doOnError(e -> log.error("[ERROR] [SELECTED-INTRODUCTION] {}", e.getMessage()));
    }

    @PutMapping("/select/{id}")
    public Single<ResponseObject<IntroductionResponse>> selectIntroduction(@PathVariable("id") String id) {
        return introductionService.onSelected(id)
                .subscribeOn(Schedulers.io())
                .map(res -> new ResponseObject<>(
                        res, HttpStatus.OK, "Select introduction successfully"
                ))
                .doOnError(e -> log.error("[ERROR] [SELECT-INTRODUCTION] {}", e.getMessage()));
    }

}
