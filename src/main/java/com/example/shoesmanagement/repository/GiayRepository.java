package com.example.shoesmanagement.repository;

import com.example.shoesmanagement.model.ChatLieu;
import com.example.shoesmanagement.model.Giay;
import com.example.shoesmanagement.model.Hang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GiayRepository extends JpaRepository<Giay, UUID> {

    Giay findByTenGiay(String tenGiay);

    @Query("SELECT g FROM Giay g where g.maGiay = :searchTerm or g.tenGiay = :searchTerm or g.hang.tenHang = :searchTerm or g.chatLieu.tenChatLieu = :searchTerm ")
    List<Giay> customSearch(@Param("searchTerm") String searchTerm);

    List<Giay> findByHang(Hang hang);

    List<Giay> findByChatLieu(ChatLieu chatLieu);

    List<Giay> findByTrangThai(int trangThai);

    List<Giay> findAllByOrderByTgThemDesc();

    Giay findByMaGiay(String maGiay);
}
