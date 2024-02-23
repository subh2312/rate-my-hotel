package org.subhankar.addressservice.model.DO;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "addresses")
public class AddressDO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
