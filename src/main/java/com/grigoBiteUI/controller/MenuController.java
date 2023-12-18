package com.grigoBiteUI.controller;

import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.dto.canteen.RequestCUMenu;
import com.grigoBiteUI.service.CanteenList.MenuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/{tenantId}")
    public String getAllMenusByTenantId(@PathVariable Long tenantId, Model model) {
        List<Menu> menus = menuService.getAllMenusByTenantId(tenantId);
        model.addAttribute("menus", menus);
        model.addAttribute("tenantId", tenantId);
        return "menu-list";
    }

    @GetMapping("/{tenantId}/{id}")
    public ResponseEntity<Menu> getMenuByIdAndTenantId(@PathVariable Long tenantId, @PathVariable Long id) {
        Optional<Menu> menu = menuService.getMenuByIdAndTenantId(id, tenantId);
        return menu.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search/{tenantId}")
    public ResponseEntity<List<Menu>> searchMenuByNameAndTenant(@PathVariable Long tenantId,
                                                                @RequestParam String keyword) {
        List<Menu> menus = menuService.searchMenuByNameAndTenant(keyword, tenantId);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @GetMapping("/sorted/{tenantId}")
    public ResponseEntity<List<Menu>> getAllMenusSortedByPriceDesc(@PathVariable Long tenantId) {
        List<Menu> menus = menuService.getAllMenusSortedByPriceDesc(tenantId);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    @PostMapping("/create/{tenantId}")
    @PreAuthorize("hasAnyAuthority('menu:crud')")
    public ResponseEntity<Menu> createMenu(@PathVariable Long tenantId,
                                           RequestCUMenu requestCUMenu) {
        Menu createdMenu = menuService.createMenu(requestCUMenu, tenantId);
        return new ResponseEntity<>(createdMenu, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('menu:crud')")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id,
                                           @RequestBody @Valid RequestCUMenu requestCUMenu) {
        Optional<Menu> updatedMenu = menuService.updateMenu(id, requestCUMenu);
        return updatedMenu.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('menu:crud')")
    public ResponseEntity<Void> deleteMenu(@PathVariable String id) {
        menuService.deleteMenuById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

