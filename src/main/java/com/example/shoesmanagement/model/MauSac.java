package com.example.shoesmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "mau_sac")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mau")
    private UUID idMau;

    @NotBlank
    @Column(name = "ma")
    private String ma;

    @NotBlank
    @Column(name = "ma_mau")
    private String maMau;

    @NotBlank
    @Column(name = "ten_mau")
    private String tenMau;

    @Column(name = "trang_thai")
    private int trangThai;
    @Column(name = "tg_them")
    private Date tgThem;

    @Column(name = "tg_sua")
    private Date tgSua;

}
