package com.grigoBiteUI.service.CanteenList;

import com.grigoBiteUI.dto.canteen.RequestCTenant;
import com.grigoBiteUI.dto.canteen.RequestUTenant;
import com.grigoBiteUI.exceptions.CanteenNotFoundException;
import com.grigoBiteUI.exceptions.TenantNotFoundException;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.repository.CanteenRepository;
import com.grigoBiteUI.repository.TenantRepository;
import com.grigoBiteUI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;
    private final CanteenRepository canteenRepository;

    private final UserRepository userRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository, CanteenRepository canteenRepository, UserRepository userRepository) {
        this.tenantRepository = tenantRepository;
        this.canteenRepository = canteenRepository;
        this.userRepository = userRepository;
    }

    public List<Tenant> getAllTenants(Long id) {
        return tenantRepository.findByCanteenId(id);
    }

    public Optional<Tenant> getTenantById(Long canteenId, Long id) {
        return tenantRepository.findByIdAndCanteenId(id, canteenId);
    }

    public Tenant createTenant(Long canteenId, RequestCTenant requestCTenant) {
        Canteen canteen = getCanteenById(canteenId);
        Tenant tenant = mapRequestToTenant(requestCTenant);
        tenant.setCanteen(canteen);
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, RequestUTenant requestUTenant) {
        Tenant existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with ID " + id + " not found"));
        mapRequestToExistingTenant(requestUTenant, existingTenant);
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

    private Tenant mapRequestToTenant(RequestCTenant requestCTenant) {
        Penjual penjual = (Penjual) userRepository.findById(requestCTenant.getIdPenjual());
        return Tenant.builder()
                .namaTenant(requestCTenant.getNamaTenant())
                .deskripsiTenant(requestCTenant.getDeskripsiTenant())
                .penjual(penjual)
                .build();
    }

    private void mapRequestToExistingTenant(RequestUTenant requestUTenant, Tenant existingTenant) {
        existingTenant.setNamaTenant(requestUTenant.getNamaTenant());
        existingTenant.setDeskripsiTenant(requestUTenant.getDeskripsiTenant());
    }
}
