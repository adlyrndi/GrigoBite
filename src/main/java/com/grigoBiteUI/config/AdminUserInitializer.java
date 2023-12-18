package com.grigoBiteUI.config;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.model.CanteenList.Tenant;
import com.grigoBiteUI.model.auth.Penjual;
import com.grigoBiteUI.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.grigoBiteUI.model.auth.User;
import com.grigoBiteUI.repository.UserRepository;
import com.grigoBiteUI.repository.CanteenRepository;

@Component
public class AdminUserInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CanteenRepository canteenRepository;

    @Autowired
    private TenantRepository tenantRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createAdminUser();
    }

    private void createAdminUser() {
        if (!userRepository.existsByUsername("admin")) {
            User adminUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .active(true)
                    .role("ADMIN")
                    .build();
            userRepository.save(adminUser);

            Canteen canteen = Canteen.builder()
                    .alamat("Jl. Fasilkom Gedung Lama")
                    .namaKantin("Balgebun")
                    .fakultas("Fasilkom")
                    .build();
            canteenRepository.save(canteen);


            Penjual penjual = Penjual.builder()
                    .username("cipeng")
                    .nickname("Cipengyajuj")
                    .active(true)
                    .password(passwordEncoder.encode("yajuj"))
                    .role("PENJUAL")
                    .saldo(0)
                    .build();

            userRepository.save(penjual);








        }
    }
}

