package com.vinicius.vs.associate.service;

import com.vinicius.vs.associate.dtos.AssociateDTO;
import com.vinicius.vs.associate.dtos.CreateAssociateDTO;
import com.vinicius.vs.associate.model.Associate;

import java.util.List;

public interface IAssociateService {
    AssociateDTO createAssociate(CreateAssociateDTO createAssociateDTO);

    List<AssociateDTO> listAssociates();

    Associate listAssociates(long id);

    void updateAssociate(AssociateDTO associateDTO);
}
