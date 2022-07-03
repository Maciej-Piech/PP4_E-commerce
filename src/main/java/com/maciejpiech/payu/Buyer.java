package com.maciejpiech.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer {
    String email;
    String phone;
    String firstName;
    String lastName;
    String language;





// EXAMPLE:
//         "email": "michael@jackson.com",
//         "phone": "123456789",
//         "firstName": "Michael",
//         "lastName": "Jackson",
//         "language": "eng"
}
