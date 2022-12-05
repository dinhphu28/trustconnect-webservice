package com.idb.apispr.Entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "app_fd_tls_su_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SourceCall {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @Column(name = "c_phanloai")
    private String phanLoai;

    @Column(name = "c_ghichu")
    private String ghiChu;

    @Column(name = "c_label")
    private String label;
}
