package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Nasa {
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public Nasa() {
    }

    public Nasa(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getService_version() {
        return service_version;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nasa nasa = (Nasa) o;
        return Objects.equals(date, nasa.date) &&
                Objects.equals(explanation, nasa.explanation) &&
                Objects.equals(hdurl, nasa.hdurl) &&
                Objects.equals(media_type, nasa.media_type) &&
                Objects.equals(service_version, nasa.service_version) &&
                Objects.equals(title, nasa.title) &&
                Objects.equals(url, nasa.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, explanation, hdurl, media_type, service_version, title, url);
    }

    @Override
    public String toString() {
        return "Nasa{" +
                "date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media_type='" + media_type + '\'' +
                ", service_version='" + service_version + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
