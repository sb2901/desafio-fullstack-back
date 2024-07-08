package com.test.fullstack.test.configuration;

import com.test.fullstack.test.dto.CostumerContactDto;
import com.test.fullstack.test.entities.CostumerContact;
import com.test.fullstack.test.entities.enums.ContactType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        Converter<ContactType, Integer> typeConverter =
                ctx -> ctx.getSource() == null ? null : ctx.getSource().getId();

        //Type CostumerContact -> CostumerContactDto
        modelMapper.typeMap(CostumerContact.class, CostumerContactDto.class)
                .addMappings(mapper ->
                        mapper.using(typeConverter)
                                .map(CostumerContact::getType, CostumerContactDto::setType));

        //Type CostumerContactDto -> CostumerContact
        Converter<Integer,ContactType > typeConverterDto =
                ctx -> ctx.getSource() == null ? null : ContactType.valueOf(ctx.getSource());

        modelMapper.typeMap(CostumerContactDto.class, CostumerContact.class)
                .addMappings(mapper ->
                        mapper.using(typeConverterDto)
                                .map(CostumerContactDto::getType, CostumerContact::setType));

        return modelMapper;
    }
}
