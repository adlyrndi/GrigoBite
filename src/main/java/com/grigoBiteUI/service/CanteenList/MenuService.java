package com.grigoBiteUI.service.CanteenList;

import com.grigoBiteUI.dto.canteen.RequestCUMenu;
import com.grigoBiteUI.exceptions.TenantNotFoundException;
import com.grigoBiteUI.model.CanteenList.Menu;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.repository.MenuRepository;
import com.grigoBiteUI.repository.TenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final TenantRepository tenantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, TenantRepository tenantRepository) {
        this.menuRepository = menuRepository;
        this.tenantRepository = tenantRepository;
    }

    public List<Menu> getAllMenusByTenantId(Long tenantId) {
        return menuRepository.findAllByTenantId(tenantId);
    }

    public Optional<Menu> getMenuByIdAndTenantId(Long id, Long tenantId) {
        return menuRepository.findByIdAndTenantId(id, tenantId);
    }

    public List<Menu> searchMenuByNameAndTenant(String keyword, Long tenantId) {
        return menuRepository.findByTenantIdAndNamaContaining(tenantId, keyword);
    }

    public List<Menu> getAllMenusSortedByPriceDesc(Long tenantId) {
        return menuRepository.findAllByTenantIdOrderByHargaDesc(tenantId);
    }

    public Menu createMenu(@Valid RequestCUMenu requestCUMenu, Long tenantId) {
        Tenant tenant = getTenantById(tenantId);

        Menu menu = Menu.builder()
                .nama(requestCUMenu.getNama())
                .deskripsi(requestCUMenu.getDeskripsi())
                .harga(requestCUMenu.getHarga())
                .tenant(tenant)
                .build();

        return menuRepository.save(menu);
    }

    public Optional<Menu> updateMenu(Long id, RequestCUMenu requestCUMenu) {
        Optional<Menu> existingMenu = menuRepository.findById(id);

        return existingMenu.map(menu -> {
            menu.setNama(requestCUMenu.getNama());
            menu.setDeskripsi(requestCUMenu.getDeskripsi());
            menu.setHarga(requestCUMenu.getHarga());
            return menuRepository.save(menu);
        });
    }

    public void deleteMenuById(Long id) {
        menuRepository.deleteById(id);
    }

    private Tenant getTenantById(Long tenantId) {
        // Retrieve the Tenant entity based on tenantId
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new TenantNotFoundException("Tenant with ID " + tenantId + " not found"));
    }
}
