package org.example.apiPresentation.dto.inDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPin {
    private String accountId;
    private String oldPassword;
    private String newPassword;
}
