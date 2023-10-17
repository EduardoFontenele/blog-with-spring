package com.edublog.adapter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountAdapter {

    AccountAdapter INSTANCE = Mappers.getMapper(AccountAdapter.class);
}
