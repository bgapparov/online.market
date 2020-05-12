package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import javassist.NotFoundException;

import java.util.List;

public interface AddressService {
    Address createAddress(Buyer buyer, Address address);
    List<Address> getAddressesByBuyer(Buyer buyer);
    Address getMyAddress(Buyer buyer, Long id);
    Address updateAddress(Buyer buyer, long id, Address address) throws NotFoundException;
    void deleteAddress(Buyer buyer,long id) throws NotFoundException;
}
