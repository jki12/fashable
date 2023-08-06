package Hackerton.fashable.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.time.OffsetDateTime;

@MappedSuperclass
public class BaseEntity {
    private OffsetDateTime createdDate;

    @PrePersist
    public void setCreatedDate() {
        this.createdDate = OffsetDateTime.now();
    }

    protected void setEmail(String email) {
    }

    protected void setNickName(String nickName) {
    }

    protected void setPw(String pw) {
    }
}
