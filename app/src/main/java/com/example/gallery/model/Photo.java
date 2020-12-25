package com.example.gallery.model;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "owner",
        "secret",
        "server",
        "farm",
        "title",
        "ispublic",
        "isfriend",
        "isfamily",
        "url_s",
        "height_s",
        "width_s"
})
@Entity
public class Photo {

    @PrimaryKey
    @NonNull
    @JsonProperty("id")
    private String id;
    @JsonProperty("owner")
    private String owner;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("server")
    private String server;
    @JsonProperty("farm")
    private Integer farm;
    @JsonProperty("title")
    private String title;
    @JsonProperty("ispublic")
    private Integer ispublic;
    @JsonProperty("isfriend")
    private Integer isfriend;
    @JsonProperty("isfamily")
    private Integer isfamily;
    @JsonProperty("url_s")
    private String urlS;
    @JsonProperty("height_s")
    private int heightS;
    @JsonProperty("width_s")
    private int widthS;


    /**
     * No args constructor for use in serialization
     */
    public Photo() {
        id = "1";
    }

    public Photo(@NonNull String id, String owner, String secret, String server, int farm, String title, int ispublic, int isfriend, int isfamily, String urlS, int heightS, int widthS) {
        super();
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.ispublic = ispublic;
        this.isfriend = isfriend;
        this.isfamily = isfamily;
        this.urlS = urlS;
        this.heightS = heightS;
        this.widthS = widthS;
    }

    @NonNull
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    @JsonProperty("farm")
    public Integer getFarm() {
        return farm;
    }

    @JsonProperty("farm")
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("ispublic")
    public Integer getIspublic() {
        return ispublic;
    }

    @JsonProperty("ispublic")
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    @JsonProperty("isfriend")
    public Integer getIsfriend() {
        return isfriend;
    }

    @JsonProperty("isfriend")
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    @JsonProperty("isfamily")
    public Integer getIsfamily() {
        return isfamily;
    }

    @JsonProperty("isfamily")
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    @JsonProperty("url_s")
    public String getUrlS() {
        return urlS;
    }

    @JsonProperty("url_s")
    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }

    @JsonProperty("height_s")
    public int getHeightS() {
        return heightS;
    }

    @JsonProperty("height_s")
    public void setHeightS(int heightS) {
        this.heightS = heightS;
    }

    @JsonProperty("width_s")
    public int getWidthS() {
        return widthS;
    }

    @JsonProperty("width_s")
    public void setWidthS(int widthS) {
        this.widthS = widthS;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("owner", owner).append("secret", secret).append("server", server).append("farm", farm).append("title", title).append("ispublic", ispublic).append("isfriend", isfriend).append("isfamily", isfamily).append("urlS", urlS).append("heightS", heightS).append("widthS", widthS).toString();
    }
}