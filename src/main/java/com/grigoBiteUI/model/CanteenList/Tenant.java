package com.grigoBiteUI.model.CanteenList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.grigoBiteUI.model.auth.Penjual;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"tenant\"")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namaTenant;
    private String deskripsiTenant;

    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> listMenu;

    @OneToOne
    private Penjual penjual;
    @ManyToOne
    private Canteen canteen;


    public Long getTenantId() {
        return id;
    }
}
