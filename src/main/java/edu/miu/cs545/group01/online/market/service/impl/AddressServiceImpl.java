package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.Buyer;
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
    public Address createAddress(Buyer buyer, Address address){
        address.setBuyer(buyer);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressesByBuyer(Buyer buyer){
        return addressRepository.findAllByBuyer(buyer);
    }

    @Override
    public Address getMyAddress(Buyer buyer, Long id){
        Address result = addressRepository.findById(id).orElse(null);
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return null;
        }
        return result;
    }

    public Address updateAddress(Buyer buyer, long id, Address address) throws NotFoundException{
        Address adr = addressRepository.findById(id).orElseThrow(()->new NotFoundException("Address is not found"));
        if(adr != null && adr.getBuyer().getId() != buyer.getId()){
            return null;
        }
        adr.setCity(address.getCity());
        adr.setCountry(address.getCountry());
        adr.setPhoneNo(address.getPhoneNo());
        adr.setState(address.getState());
        adr.setStreet(address.getStreet());
        adr.setZipcode(address.getZipcode());
        return addressRepository.save(adr);
    }

    @Override
    public void deleteAddress(Buyer buyer, long id) throws NotFoundException {
        Address result = addressRepository.findById(id).orElseThrow(()->new NotFoundException("Address is not found"));
        if(result != null && result.getBuyer().getId() != buyer.getId()){
            return;
        }
        addressRepository.deleteById(result.getId());
    }
}
