package com.lwl.currency.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

      @Column(name = "created_date",nullable = false,updatable = false)
      private LocalDateTime createdDate;
      @Column(name = "updated_date",nullable = false)
      private LocalDateTime updatedDate;
      @Column(name = "created_by",nullable = false,updatable = false)
      private String createdBy;
      @Column(name="updated_by",nullable = false)
      private String updatedBy;


      @PrePersist
      public void prePersist(){
          createdDate = LocalDateTime.now(ZoneOffset.UTC);
          updatedDate = LocalDateTime.now(ZoneOffset.UTC);
          createdBy = "User123";
          updatedBy = "User123";
      }
      @PreUpdate
      public void preUpdate(){
        updatedDate = LocalDateTime.now(ZoneOffset.UTC);
        updatedBy = "User123";
      }
      abstract void onPersists();
}
