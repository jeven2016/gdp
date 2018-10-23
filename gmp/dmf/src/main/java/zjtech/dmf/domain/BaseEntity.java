package zjtech.dmf.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements IDEntity<Long> {
    private Long id;

    @Override
    @Id
    @Column(name = "id", nullable = false)
    @Type(type = "java.lang.Long")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Serializable id) {
        if (id instanceof Long) {
            this.id = (Long) id;
            return;
        }

        if (id instanceof String) {
            this.id = Long.valueOf((String) id);
            return;
        }
        throw new IllegalArgumentException("Invalid id (" + id + ").");
    }
}
