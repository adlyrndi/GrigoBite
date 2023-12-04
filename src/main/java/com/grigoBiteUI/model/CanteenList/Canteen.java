package com.grigoBiteUI.model.CanteenList;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "\"canteen\"")
public class Canteen {

    @Id
    @GeneratedValue
    private Long id;
    private String alamat;
    private String namaKantin;
    private String fakultas;
    @JsonIgnore
    @OneToMany(mappedBy = "canteen", cascade = CascadeType.ALL)
    private List<Tenant> listTenant;
}
