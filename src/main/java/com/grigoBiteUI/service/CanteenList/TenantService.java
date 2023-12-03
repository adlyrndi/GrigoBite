package com.grigoBiteUI.service.CanteenList;

import com.grigoBiteUI.dto.canteen.RequestCUTenant;
import com.grigoBiteUI.exceptions.CanteenNotFoundException;
import com.grigoBiteUI.exceptions.TenantNotFoundException;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.repository.CanteenRepository;
import com.grigoBiteUI.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final CanteenRepository canteenRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository, CanteenRepository canteenRepository) {
        this.tenantRepository = tenantRepository;
        this.canteenRepository = canteenRepository;
    }

    public List<Tenant> getAllTenants(Long id) {
        return tenantRepository.findByCanteenId(id);
    }

    public Optional<Tenant> getTenantById(Long canteenId, Long id) {
        return tenantRepository.findByIdAndCanteenId(id, canteenId);
    }

    public Tenant createTenant(Long canteenId, RequestCUTenant requestCUTenant) {
        Canteen canteen = getCanteenById(canteenId);
        Tenant tenant = mapRequestToTenant(requestCUTenant);
        tenant.setCanteen(canteen);
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, RequestCUTenant requestCUTenant) {
        Tenant existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with ID " + id + " not found"));
        mapRequestToExistingTenant(requestCUTenant, existingTenant);
        return tenantRepository.save(existingTenant);
    }

    public void deleteTenantById(Long id) {
        tenantRepository.deleteById(id);
    }

    public List<Tenant> getTenantsByCanteenId(Long canteenId) {
        return tenantRepository.findByCanteenId(canteenId);
    }

    private Canteen getCanteenById(Long canteenId) {
        return canteenRepository.findById(canteenId)
                .orElseThrow(() -> new CanteenNotFoundException("Canteen with ID " + canteenId + " not found"));
    }

    private Tenant mapRequestToTenant(RequestCUTenant requestCUTenant) {
        return Tenant.builder()
                .namaTenant(requestCUTenant.getNamaTenant())
                .deskripsiTenant(requestCUTenant.getDeskripsiTenant())
                .namaPenjual(requestCUTenant.getNamaPenjual())
                .build();
    }

    private void mapRequestToExistingTenant(RequestCUTenant requestCUTenant, Tenant existingTenant) {
        existingTenant.setNamaTenant(requestCUTenant.getNamaTenant());
        existingTenant.setDeskripsiTenant(requestCUTenant.getDeskripsiTenant());
        existingTenant.setNamaPenjual(requestCUTenant.getNamaPenjual());
    }
}
