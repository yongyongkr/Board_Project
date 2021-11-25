package boardprogram.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseTimeEntity {

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

}
