package com.edublog.usecase;

import com.edublog.domain.dto.account.AccountInfoDto;
import com.edublog.domain.dto.account.AccountRegisterDto;

public interface RegistrationService {
    AccountInfoDto registerAccount(AccountRegisterDto accountRegisterDto);
}
