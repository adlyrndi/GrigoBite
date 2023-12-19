package com.grigoBiteUI.service.CanteenList;

import com.grigoBiteUI.dto.canteen.RequestCUCanteen;
import com.grigoBiteUI.exceptions.CanteenNotFoundException;
import com.grigoBiteUI.model.CanteenList.Canteen;
import com.grigoBiteUI.repository.CanteenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanteenListService {

    private final CanteenRepository canteenRepository;

    @Autowired
    public CanteenListService(CanteenRepository canteenRepository) {
        this.canteenRepository = canteenRepository;
    }

    // Retrieve all canteens
    public List<Canteen> getAllCanteens() {
        return canteenRepository.findAll();
    }

    // Retrieve a canteen by its ID
    public Optional<Canteen> getCanteenById(Long id) {
        return canteenRepository.findById(id);
    }

    public Canteen createCanteen(RequestCUCanteen requestCUCanteen) {
        Canteen canteen = Canteen.builder()
                .alamat(requestCUCanteen.getAlamat())
                .namaKantin(requestCUCanteen.getNamaKantin())
                .fakultas(requestCUCanteen.getFakultas())
                .build();
        canteenRepository.save(canteen);
        return canteen;
    }

    public Canteen updateCanteen(Long id, RequestCUCanteen requestCUCanteen) {
        Canteen existingCanteen = canteenRepository.findById(id)
                .orElseThrow(() -> new CanteenNotFoundException("Canteen with ID " + id + " not found"));
        Canteen canteen = Canteen.builder().id(id)
                .alamat(requestCUCanteen.getAlamat())
                .namaKantin(requestCUCanteen.getNamaKantin())
                .fakultas(requestCUCanteen.getFakultas())
                .build();

        this.canteenRepository.save(canteen);
        return canteen;
    }

    // Delete a canteen by its ID
    public void deleteCanteenById(Long id) {
        canteenRepository.deleteById(id);
    }

    private Canteen mapRequestToCanteen(RequestCUCanteen requestCUCanteen) {
        return Canteen.builder()
                .alamat(requestCUCanteen.getAlamat())
                .namaKantin(requestCUCanteen.getNamaKantin())
                .fakultas(requestCUCanteen.getFakultas())
                .build();
    }
}
