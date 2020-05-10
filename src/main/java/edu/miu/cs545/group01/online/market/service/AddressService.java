package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(Address address);
    List<Address> getAddresses();
    Address getAddress(Long id);
    Address updateAddress(Long id, Address address) throws Exception;
    Address deleteAddress(Long id) throws Exception;
}
