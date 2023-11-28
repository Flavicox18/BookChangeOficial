package pe.edu.upao.bookchange.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBooksResponse {

    @JsonProperty("items")
    private List<Item> items;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        @JsonProperty("volumeInfo")
        private VolumeInfo volumeInfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        @JsonProperty("title")
        private String title;

        @JsonProperty("subtitle")
        private String subtitle;

        @JsonProperty("authors")
        private List<String> authors;

        @JsonProperty("publisher")
        private String publisher;

        @JsonProperty("publishedDate")
        private String publishedDate;

        @JsonProperty("description")
        private String description;

        @JsonProperty("industryIdentifiers")
        private List<IndustryIdentifier> industryIdentifiers;

        @JsonProperty("imageLinks")
        private ImageLinks imageLinks;

        @JsonProperty("categories")
        private List<String> categories;

        @JsonProperty("id")
        private Long id;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ImageLinks {
            @JsonProperty("smallThumbnail")
            private String smallThumbnail;

            @JsonProperty("thumbnail")
            private String thumbnail;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IndustryIdentifier {
        @JsonProperty("type")
        private String type;

        @JsonProperty("identifier")
        private String identifier;
    }

}
