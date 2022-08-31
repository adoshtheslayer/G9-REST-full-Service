package uz.pdp.g9restfulservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.g9restfulservice.entity.Characteristic;
import uz.pdp.g9restfulservice.payload.ApiResponse;
import uz.pdp.g9restfulservice.repository.CharacteristicRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacteristicService {


    private final CharacteristicRepository characteristicRepo;


    public Characteristic getCharacterById(Long id) {
        Optional<Characteristic> characterById = characteristicRepo.findById(id);

            return characterById.orElse(null);
        }


    public  List<Characteristic> getCharacterAll() {
        List<Characteristic> characterAll = characteristicRepo.findAll();
        return characterAll;
    }


    public ApiResponse deleteCharacter(Long id) {

        boolean existsById = characteristicRepo.existsById(id);

        if (!existsById) {

            return new ApiResponse("Character not found ", false);
        }
        characteristicRepo.deleteById(id);
        return new ApiResponse("Deleted success", true);
    }

    public ApiResponse updateCharacterById(Long id, Characteristic characteristic) {

        boolean existsById = characteristicRepo.existsById(id);

        if (!existsById) {

            return new ApiResponse("Character not found ", false);
        }
        characteristicRepo.save(characteristic);
        return new ApiResponse("Update success", true);
    }

    public ApiResponse saveCharacter(Characteristic characteristic) {


        Characteristic saveCharacter = characteristicRepo.save(characteristic);

        boolean existsById = characteristicRepo.existsById(saveCharacter.getId());

        if (!existsById) {
            return new ApiResponse("Character no added ", false);
        }
        return new ApiResponse("Character success added ", true);

    }
}
