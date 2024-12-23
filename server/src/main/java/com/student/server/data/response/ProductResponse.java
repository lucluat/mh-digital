package com.student.server.data.response;

import java.util.List;

public record ProductResponse(String id, String title, String coverImage, String footerImage, List<Content> content,
                              String link) {

    public record Content(String title, String image, List<SubContent> subContents) {

        public record SubContent(String title, String passage) {

        }

    }

}
