package com.example.shoesmanagement.controller;

import com.example.shoesmanagement.model.MauSac;
import com.example.shoesmanagement.repository.MauSacRepository;
import com.example.shoesmanagement.service.MauSacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/manage")
public class MauSacController {
    @Autowired
    MauSacRepository msRepo;

    @Autowired
    MauSacService msSer;

    @GetMapping("/mau-sac")
    public String hienThi(Model model) {
        //model.addAttribute("mauSac", msRepo.findAll());
        model.addAttribute("mauSac", msSer.findAll());
        model.addAttribute("mauSacAdd", new MauSac());
        return "manage/mau-sac";
    }

    @PostMapping("/mau-sac/viewAdd/add")
    public String save(@ModelAttribute("mauSacAdd") MauSac mauSac) {
        msSer.save(mauSac);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mau-sac/viewUpdate/{idMau}")
    public String viewUpdate(@PathVariable("idMau") UUID idMau, Model model) {
        MauSac mauSac = msSer.getOne(idMau);
        model.addAttribute("mauSac", mauSac);
        return "manage/update-mau-sac";
    }

    @PostMapping("/mau-sac/update/{idMau}")
    public String update(@ModelAttribute("mauSac") MauSac ms, UUID idMau){
        System.out.println(idMau);
        MauSac mauSac = msSer.getOne(idMau);
        ms.setTgThem(mauSac.getTgThem());
        msSer.update(ms);
        return "redirect:/manage/mau-sac";
    }

    @GetMapping("/mauSac/filter")
    public String loc(Model model, @RequestParam("trangThai") int trangThai){
        List<MauSac> locTT = msSer.loc(trangThai);
        model.addAttribute("mauSac", locTT);
        model.addAttribute("mauSacAdd", new MauSac());
        return "manage/mau-sac";
    }
}
