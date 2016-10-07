package me.dong.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.dong.config.SystemPropertiesConfig;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
public class Product extends AbstractEntity<Long> {

    @Id
    @GeneratedValue
    @JsonProperty
    private Long id;

    @Column(length = 30)
    @JsonProperty
    private String name;

    @Column
    @JsonProperty
    private Double price;

    @Column(name = "image_file_name", length = 100)
    private String imageFileName;

    @Column(length = 10)
    @JsonProperty
    private String color;

    @Lob
    @JsonProperty
    private String description;

    /**
     * 업로드된 이미지를 다운받을 수 있는 url 제공
     * @return image url
     */
    @JsonProperty("imageUrl")
    public String getImageUrl(){
        return String.format("%s/product/%d/%s",
                System.getProperty(SystemPropertiesConfig.STORAGE_URI), this.id, this.imageFileName);
    }

    /**
     * 물리적인 image upload path 제공
     * @return upload path
     */
    @Transient
    public String getImageUploadPath(){
        return String.format("%s/product/%d", System.getProperty(SystemPropertiesConfig.STORAGE_PATH), this.id);
    }
}
