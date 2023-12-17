package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.canteen.RequestCUCanteen;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.service.CanteenList.CanteenListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/canteens")
public class CanteenListController {

    private final CanteenListService canteenListService;

    @GetMapping("")
    public String showCanteensPage(Model model) {
        List<Canteen> canteens = canteenListService.getAllCanteens();
        model.addAttribute("canteens", canteens);
        return "canteen-list";
    }

    @Autowired
    public CanteenListController(CanteenListService canteenListService) {
        this.canteenListService = canteenListService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Canteen>> getAllCanteens() {
        List<Canteen> canteens = canteenListService.getAllCanteens();
        return ResponseEntity.ok(canteens);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Canteen> getCanteenById(@PathVariable Long id) {
        Optional<Canteen> canteen = canteenListService.getCanteenById(id);
        return canteen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('canteen:crud')")
    public ResponseEntity<Canteen> createCanteen(RequestCUCanteen requestCUCanteen) {
        Canteen createdCanteen = canteenListService.createCanteen(requestCUCanteen);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('canteen:crud')")
    public String updateCanteen(@PathVariable Long id, @RequestBody RequestCUCanteen requestCUCanteen) {
        Canteen updatedCanteen = canteenListService.updateCanteen(id, requestCUCanteen);
        return "redirect:/canteens";
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('canteen:crud')")
    public ResponseEntity<Void> deleteCanteenById(@PathVariable Long id) {
        canteenListService.deleteCanteenById(id);
        return ResponseEntity.noContent().build();
    }

}
