package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.canteen.RequestCUTenant;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.service.CanteenList.CanteenListService;
import com.grigoBiteUI.service.CanteenList.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tenants")
public class TenantController {

    private final TenantService tenantService;

    private final CanteenListService canteenListService;

    @Autowired
    public TenantController(TenantService tenantService, CanteenListService canteenListService) {
        this.tenantService = tenantService;
        this.canteenListService = canteenListService;
    }

    @GetMapping("/{canteenId}")
    public String getAllTenants(@PathVariable Long canteenId, Model model) {
        List<Tenant> tenants = tenantService.getAllTenants(canteenId);
        Optional<Canteen> canteen = canteenListService.getCanteenById(canteenId);
        String canteenName = canteen.get().getNamaKantin();
        model.addAttribute("tenants", tenants);
        model.addAttribute("canteenId", canteenId);
        model.addAttribute("canteen", canteenName);
        return "tenant-list";
    }

    @GetMapping("/{canteenId}/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long canteenId, @PathVariable Long id) {
        Optional<Tenant> tenant = tenantService.getTenantById(canteenId, id);
        return tenant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create/{canteenId}")
    @PreAuthorize("hasAnyAuthority('tenant:crud', 'tenant:cru')")
    public String createTenant(@PathVariable Long canteenId, RequestCUTenant requestCUTenant, RedirectAttributes redirectAttributes) {
        Tenant createdTenant = tenantService.createTenant(canteenId, requestCUTenant);

        redirectAttributes.addAttribute("canteenId", canteenId);

        return "redirect:/tenants/{canteenId}";
    }

    @PutMapping("/update/{canteenId}/{id}")
    @PreAuthorize("hasAnyAuthority('tenant:crud', 'tenant:cru')")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody RequestCUTenant requestCUTenant) {
//        if (!isAuthenticatedUserOwnerOfTenant(authentication, id)) {
//            // If the user is not the owner, deny the update
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
        Tenant updatedTenant = tenantService.updateTenant(id, requestCUTenant);
        return new ResponseEntity<>(updatedTenant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{canteenId}/{id}")
    @PreAuthorize("hasAuthority('tenant:crud')")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long canteenId, @PathVariable Long id) {
        tenantService.deleteTenantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    private boolean isAuthenticatedUserOwnerOfTenant(Authentication authentication, Long tenantId) {
//        // Add logic to check if the authenticated user is the owner of the Tenant
//        // For example, fetch the Tenant entity and compare it with the authenticated user
//        Optional<Tenant> optionalTenant = tenantService.getTenantById(tenantId);
//        return optionalTenant.isPresent() && optionalTenant.get().getOwner().getUsername().equals(authentication.getName());
//    }


}
