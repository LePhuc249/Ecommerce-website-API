package nashtech.phucldh.ecommerce.entity;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(
        name = "image",
        indexes = {
                @Index(name = "image_index", columnList = "id, url")
        }
)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "URL is mandatory")
    @Size(min = 5, max = 200, message = "URL must be between 5 and 200 characters")
    @Column(name = "url")
    private String url;

    @NotBlank(message = "Description image is mandatory")
    @Size(min = 5, max = 200, message = "Description image must be between 5 and 200 characters")
    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createdate;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

    @Column(name = "create_by")
    private Integer createby;

    @Column(name = "isdeleted")
    private boolean isDeleted;

    @OneToOne(mappedBy = "imageOrganization")
    private Organization organization;

}