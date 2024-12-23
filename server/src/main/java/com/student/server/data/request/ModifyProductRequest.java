package com.student.server.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ModifyProductRequest(
        @NotBlank(message = "title không được trống!")
        String title,
        @NotBlank(message = "cover image không được trống!")
        String coverImage,
        @NotBlank(message = "footer image không được trống!")
        String footerImage,
        @NotNull(message = "content không được trống!")
        List<Content> content,
        @NotBlank(message = "link không được trống!")
        String link) {

    public record Content(String title, String image, List<SubContent> subContents) {

        public record SubContent(String title, String passage) {

        }

    }

}
