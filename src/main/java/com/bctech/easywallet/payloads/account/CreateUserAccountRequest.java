package com.bctech.easywallet.payloads.account;

import com.bctech.easywallet.payloads.BaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CreateUserAccountRequest extends BaseRequest {
    @NotEmpty
    @Size(min = 4, max = 4, message = "Name can not be less/greater than 4")
    private String pin;
}
