package com.student.server.data.response;

import com.student.server.data.constant.WebsiteType;

import java.util.List;

public record IntroductionResponse(String id,
                                   String coverImage,
                                   List<String> introduction,
                                   List<Partner> partner,
                                   List<FounderAdvisor> founderAdvisor,
                                   List<CompetitiveAdvantages> competitiveAdvantages,
                                   List<Content> content,
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
