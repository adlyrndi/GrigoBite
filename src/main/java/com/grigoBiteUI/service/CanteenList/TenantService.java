package com.grigoBiteUI.service.CanteenList;

import com.grigoBiteUI.dto.canteen.RequestCUTenant;
import com.grigoBiteUI.exceptions.CanteenNotFoundException;
import com.grigoBiteUI.exceptions.TenantNotFoundException;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.repository.CanteenRepository;
import com.grigoBiteUI.repository.TenantRepository;
import com.grigoBiteUI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Tenant createTenant(Long canteenId, RequestCUTenant requestCUTenant) {
        Canteen canteen = getCanteenById(canteenId);
        Tenant tenant = mapRequestToTenant(requestCUTenant);
        tenant.setCanteen(canteen);
        return tenantRepository.save(tenant);
    }

    public Tenant updateTenant(Long id, RequestCUTenant requestCUTenant) {
        Tenant existingTenant = tenantRepository.findById(id)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with ID " + id + " not found"));

        if(!getLoggedUser().getRole().equals("ADMIN")){
            Integer idUser = getLoggedUser().getId();
            if(!idUser.equals(existingTenant.getPenjual().getId())){
                throw new RuntimeException("Anda bukan Penjual pemilik tenant ini");
            }
        }

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
        int idPenjual = requestCUTenant.getIdPenjual();
        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(idPenjual));
        Penjual penjual = (Penjual) userOptional.get();
        Tenant tenant = Tenant.builder()
                .namaTenant(requestCUTenant.getNamaTenant())
                .deskripsiTenant(requestCUTenant.getDeskripsiTenant())
                .penjual(penjual)
                .build();
        penjual.setTenant(tenant);
        return tenant;
    }

    private void mapRequestToExistingTenant(RequestCUTenant requestCUTenant, Tenant existingTenant) {
        existingTenant.setNamaTenant(requestCUTenant.getNamaTenant());
        existingTenant.setDeskripsiTenant(requestCUTenant.getDeskripsiTenant());
    }

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (User) authentication.getPrincipal();
        }

        return null;
    }
}
