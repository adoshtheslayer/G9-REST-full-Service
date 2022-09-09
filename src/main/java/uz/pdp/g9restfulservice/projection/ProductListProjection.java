package uz.pdp.g9restfulservice.projection;

import uz.pdp.g9restfulservice.entity.Characteristic;

import java.util.List;

public interface ProductListProjection {

    Integer getId();

    String getName();

    List<Characteristic> getCharacteristics();

   Integer getAttachmentId();





}
