package com.grigoBiteUI.controller;

import com.grigoBiteUI.dto.canteen.RequestCUTenant;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.service.CanteenList.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/{canteenId}")
    public ResponseEntity<List<Tenant>> getAllTenants(@PathVariable Long canteenId) {
        List<Tenant> tenants = tenantService.getAllTenants(canteenId);
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    @GetMapping("/{canteenId}/{id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable Long canteenId, @PathVariable Long id) {
        Optional<Tenant> tenant = tenantService.getTenantById(canteenId, id);
        return tenant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create/{canteenId}")
    @PreAuthorize("hasAuthority('tenant:crud')")
    public ResponseEntity<Tenant> createTenant(@PathVariable Long canteenId, @RequestBody RequestCUTenant requestCUTenant) {
        Tenant createdTenant = tenantService.createTenant(canteenId, requestCUTenant);
        return new ResponseEntity<>(createdTenant, HttpStatus.CREATED);
    }

    @PutMapping("/update/{canteenId}/{id}")
    @PreAuthorize("hasAnyAuthority('tenant:crud')")
    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody RequestCUTenant requestCUTenant) {
        Tenant updatedTenant = tenantService.updateTenant(id, requestCUTenant);
        return new ResponseEntity<>(updatedTenant, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{canteenId}/{id}")
    @PreAuthorize("hasAuthority('tenant:crud')")
    public ResponseEntity<Void> deleteTenant(@PathVariable Long canteenId, @PathVariable Long id) {
        tenantService.deleteTenantById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
