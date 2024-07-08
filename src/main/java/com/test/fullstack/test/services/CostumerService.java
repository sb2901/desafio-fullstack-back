package com.test.fullstack.test.services;

import com.test.fullstack.test.dto.CostumerDto;
import com.test.fullstack.test.entities.Costumer;
import com.test.fullstack.test.repositories.CostumerContactRepository;
import com.test.fullstack.test.repositories.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository userRepository;
    @Autowired
    private CostumerContactRepository contactRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CostumerDto> getAll(){
        Iterable<Costumer> itCostumer = userRepository.findAll();
        List<CostumerDto> result = new ArrayList<>();
        itCostumer.forEach(e-> result.add(convertToDto(e)));
        return result;
    }

    public CostumerDto get(Integer id){
        Optional<Costumer>  opCostumer = userRepository.findById(id);
        return opCostumer.map(this::convertToDto).orElse(null);
    }

    public void add(CostumerDto dto) {
        Costumer costumer = convertToEntity(dto);
        userRepository.save(costumer);
    }

    public void delete(CostumerDto dto){
         userRepository.deleteById(dto.getId());
    }

    private void removeOldContacts(CostumerDto dto){
        Optional<Costumer>  opCostumer = userRepository.findById(dto.getId());
        opCostumer.ifPresent(costumer ->
                {
                    if(costumer.getContact()!=null && !costumer.getContact().isEmpty()) {
                        Set<Integer> collectBaseIds = dto.getContact()
                                .stream().map(c -> c.getId()).collect(Collectors.toSet());
                        costumer.getContact().forEach(contact ->{
                            if( !collectBaseIds.contains(contact.getId())){
                                contactRepository.delete(contact);
                            }
                        });
                    }
                }
        );
    }
    public void update(CostumerDto dto) {

        //Remove os contatos que não estão mais no dto
        removeOldContacts(dto);
        Costumer costumer = convertToEntity(dto);
        userRepository.save(costumer);
    }

    private CostumerDto convertToDto(Costumer post) {
        CostumerDto costumerDto2 = modelMapper.map(post, CostumerDto.class);
        return costumerDto2;
    }

    private Costumer convertToEntity(CostumerDto costumerDto)   {
        Costumer costumer = modelMapper.map(costumerDto, Costumer.class);
        //Temporario para entrega
        costumer.getContact().forEach( //TODO : esta mapeando errado - estudar correção correta
                    e-> e.setCostumer(costumer));
        return costumer;

    }
}
