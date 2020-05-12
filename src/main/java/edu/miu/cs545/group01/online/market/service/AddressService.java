package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Address;
import javassist.NotFoundException;

import java.util.List;

public interface AddressService {
    Address createAddress(Address address);
    List<Address> getAddresses();
    Address getAddress(Long id);
    Address updateAddress(long id, Address address) throws NotFoundException;
    void deleteAddress(long id) throws NotFoundException;
}
