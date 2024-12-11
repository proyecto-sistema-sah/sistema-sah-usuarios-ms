package com.sistema.sah.usuarios.util.mapper;

import com.sistema.sah.commons.entity.VistaTiposUsuarioEntity;
import com.sistema.sah.usuarios.dto.VistaTiposUsuarioDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VistaTiposUsuarioMapper {


    List<VistaTiposUsuarioDto> entityListToDtoList(List<VistaTiposUsuarioEntity> vistaTiposUsuarioEntity);

}
