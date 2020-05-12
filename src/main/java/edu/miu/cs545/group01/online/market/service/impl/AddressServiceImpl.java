package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.repository.AddressRepository;
import edu.miu.cs545.group01.online.market.service.AddressService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address){
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddresses(){
        return addressRepository.findAll();
    }

    @Override
    public Address getAddress(Long id){
        return addressRepository.findById(id).orElse(null);
    }

    public Address updateAddress(long id, Address address) throws NotFoundException{
        Address adr = addressRepository.findById(id).orElseThrow(()->new NotFoundException("Address is not found"));
        adr.setCity(address.getCity());
        adr.setCountry(address.getCountry());
        adr.setPhoneNo(address.getPhoneNo());
        adr.setState(address.getState());
        adr.setStreet(address.getStreet());
        adr.setZipcode(address.getZipcode());
        return addressRepository.save(adr);
    }

    @Override
    public void deleteAddress(long id){
        addressRepository.deleteById(id);
    }
}
