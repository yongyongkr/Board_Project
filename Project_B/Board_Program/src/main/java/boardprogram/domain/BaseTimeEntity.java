package boardprogram.domain;

import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseTimeEntity {

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

}
