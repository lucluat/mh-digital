package com.student.server.data.request;

import com.student.server.data.constant.WebsiteType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IntroductionRequest(
        @NotBlank(message = "cover image không được trống")
        String coverImage,
        @NotNull(message = "introduction không được trống")
        List<String> introduction,
        @NotNull(message = "partner không được trống")
        List<Partner> partner,
        @NotNull(message = "founder advisor không được trống")
        List<FounderAdvisor> founderAdvisor,
        @NotNull(message = "competitive advantages không được trống")
        List<CompetitiveAdvantages> competitiveAdvantages,
        @NotNull(message = "content không được trống")
        List<Content> content,
        @NotNull(message = "core value không được trống")
        CoreValue coreValue) {

    public record Partner(String logo) {
    }

    public record FounderAdvisor(String fullName,
                                 String position,
                                 String experience,
                                 String history,
                                 String avatar,
                                 List<Website> websites) {

        public record Website(String websiteUrl, WebsiteType websiteType) {
        }

    }

    public record CompetitiveAdvantages(String image, String title, String description) {
    }

    public record CoreValue(String image, String title, String description) {
    }

    public record Content(String title, String description, String image) {
    }

}
