package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Attachments extends BaseEntity {

    private Long taskId;
    private Long uploadedBy;

    private String fileUrl;
}
