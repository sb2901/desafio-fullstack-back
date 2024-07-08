package com.test.fullstack.test.services;

import com.test.fullstack.test.dto.CostumerContactDto;
import com.test.fullstack.test.dto.CostumerDto;
import com.test.fullstack.test.entities.Costumer;
import com.test.fullstack.test.entities.CostumerContact;
import com.test.fullstack.test.entities.enums.ContactType;
import com.test.fullstack.test.repositories.CostumerContactRepository;
import com.test.fullstack.test.repositories.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository userRepository;


    private CostumerContactRepository contactRepository;

    public List<CostumerDto> getAll(){
        Iterable<Costumer> itCostumer = userRepository.findAll();
        List<CostumerDto> result = new ArrayList<>();
        itCostumer.forEach(e-> result.add(toDto(e)));
        return result;
    }

    public CostumerDto get(Integer id){
        Optional<Costumer>  opCostumer = userRepository.findById(id);
        return opCostumer.map(this::toDto).orElse(null);
    }

    public void add(CostumerDto dto){

        Costumer costumer = Costumer.builder()
                .name(dto.name())
                .build();
        costumer.setContacts(toContactList(costumer, dto.contact()));
        userRepository.save(costumer);
    }

    public void delete(CostumerDto dto){
         userRepository.deleteById(dto.id());
    }


    private void removeOldContacts(CostumerDto dto){
        Optional<Costumer>  opCostumer = userRepository.findById(dto.id());
        opCostumer.ifPresent(costumer ->
                {
                    if(costumer.getContacts()!=null && !costumer.getContacts().isEmpty()) {
                        Set<Integer> collectBaseIds = dto.contact().stream().map(c -> c.id()).collect(Collectors.toSet());
                        costumer.getContacts().forEach(contact ->{
                            if( !collectBaseIds.contains(contact.getId())){
                                contactRepository.delete(contact);
                            }
                        });
                    }
                }
        );
    }
    public void update(CostumerDto dto){

        //Remove os contatos que não estão mais no dto
        removeOldContacts(dto);
      
        Costumer costumer = Costumer.builder()
                .id(dto.id())
                .name(dto.name())
                .build();
        costumer.setContacts(toContactList(costumer, dto.contact()));
        userRepository.save(costumer);
    }

    private Set<CostumerContact> toContactList(Costumer costumer, List<CostumerContactDto> listDto){

       return listDto==null ? Set.of():
               listDto.stream()
                .map(dto-> {
                    return CostumerContact.builder()
                            .id(dto.id())
                            .value(dto.value())
                            .type(ContactType.valueOf(dto.type()))
                            .costumer(costumer)
                            .build();
                }).collect(Collectors.toSet());
    }

    private CostumerDto toDto(Costumer c) {
        Set<CostumerContact> list = c.getContacts();
        return new CostumerDto(
                c.getId(),
                c.getName(),
                c.getCreatedAt(),
                toDto(c,c.getContacts()));
    }

    private List<CostumerContactDto> toDto(Costumer costumer, Set<CostumerContact> listContacts) {
       return listContacts== null ? null:
               listContacts.stream().map(
                    cc -> new CostumerContactDto(
                        cc.getId(),
                        cc.getType().getId(),
                        cc.getValue(),
                        costumer.getId())

                ).collect(Collectors.toList()) ;
    }

}
