package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.BillingInfoCreditCard;
import edu.miu.cs545.group01.online.market.domain.Product;
import edu.miu.cs545.group01.online.market.service.AddressService;
import edu.miu.cs545.group01.online.market.service.BillingInfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/buyer")
public class BuyerController extends BaseController{

    @Autowired
    BillingInfoService billingInfoService;
    @Autowired
    AddressService addressService;

    @GetMapping("/")
    public String buyer(){
        return "buyer/buyerCabinet";
    }

    @GetMapping("/billing/list")
    public String getBills(Model model){
        model.addAttribute("bills", billingInfoService.getBillsByBuyer(getCurrentBuyer()));
        return "buyer/billing/list";
    }

    @GetMapping("/billing/edit/{billingId}")
    public String getBilling(@PathVariable("billingId") long id, Model model){
        model.addAttribute("billing", billingInfoService.getBilling(id));
        return "buyer/billing/edit";
    }

    @PostMapping("/billing/edit/{billingId}")
    public String editBilling(@PathVariable("billingId") long id, BillingInfoCreditCard billingInfo) throws NotFoundException{
        BillingInfo updateBilling = billingInfoService.updateCreditCard(id, billingInfo);
        return "redirect:/buyer/billing/list";
    }

    @GetMapping("/billing/save")
    public String createBilling(@ModelAttribute("billing") BillingInfo billingInfo){
        return "buyer/billing/create";
    }

    @PostMapping("/billing/save")
    public String saveBilling(@ModelAttribute("billing") BillingInfo billingInfo){
        BillingInfo billingInfo1 = billingInfoService.createBilling(billingInfo);
        return "redirect:/buyer/billing/list";
    }

    @DeleteMapping("/billing/delete/{billingId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBilling(@PathVariable("billingId") long id)  throws NotFoundException{
        billingInfoService.deleteBilling(id);
    }

        @GetMapping("/address/list")
    public String getAddresses(Model model){
        model.addAttribute("addresses", addressService.getAddresses());
        return "buyer/address/list";
    }

    @GetMapping("/address/edit/{addressId}")
    public String getAddress(@PathVariable("addressId") long id, Model model){
        model.addAttribute("address",addressService.getAddress(id));
        return "buyer/address/edit";
    }

    @PostMapping("/address/edit/{addressId}")
    public String editAddress(@PathVariable("addressId") long id, Address address) throws NotFoundException{
        Address updateAddress = addressService.updateAddress(id, address);
        return "redirect:/buyer/address/list";
    }

    @GetMapping("/address/save")
    public String createAddress(@ModelAttribute("address") Address address){
        return "buyer/address/create";
    }

    @PostMapping("/address/save")
    public String saveAddress(Address address){
        Address address1 = addressService.createAddress(address);
        return "redirect:/buyer/address/list";
    }

    @DeleteMapping("/address/delete/{addressId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("addressId") long addressId) throws NotFoundException {
        addressService.deleteAddress(addressId);
    }
}
