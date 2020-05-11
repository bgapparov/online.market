package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.User;
import edu.miu.cs545.group01.online.market.service.AddressService;
import edu.miu.cs545.group01.online.market.service.BillingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        model.addAttribute("bills", billingInfoService.getBills());
        return "buyer/billing/list";
    }

    @GetMapping("/billing/get")
    public String getBilling(@RequestParam("id") long id, Model model){
        model.addAttribute("billing", billingInfoService.getBilling(id));
        return "buyer/billing/details";
    }

    @GetMapping("/billing/save")
    public String createBilling(){
        return "buyer/billing/create";
    }

    @PostMapping("/billing/save")
    public String saveBilling(BillingInfo billingInfo, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("billingInfo",billingInfoService.createBilling(billingInfo));
        return "redirect:/buyer/billing/details";
    }

    @GetMapping("/billing/details")
    public String detailsBilling(BillingInfo billingInfo, Model model){
        model.addAttribute("billingInfo",billingInfo);
        return "buyer/billing/details";
    }

    @DeleteMapping("/billing/delete")
    public String deleteBilling(BillingInfo billingInfo) throws Exception{
        billingInfoService.deleteBilling(billingInfo.getId());
        return "buyer/billing/delete";
    }

        @GetMapping("/address/list")
    public String getAddresses(Model model){
        model.addAttribute("addresses", addressService.getAddresses());
        return "buyer/address/list";
    }

    @GetMapping("/address/get")
    public String getAddress(@RequestParam("id") long id, Model model){
        model.addAttribute("addresses", addressService.getAddress(id));
        return "buyer/address/details";
    }

    @GetMapping("/address/save")
    public String createAddress(){
        return "buyer/address/create";
    }

    @PostMapping("/address/save")
    public String saveAddress(Address address, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("address",addressService.createAddress(address));
        return "redirect:/buyer/address/details";
    }

    @GetMapping("/address/details")
    public String detailsAddress(Address address, Model model){
        model.addAttribute("address",address);
        return "buyer/address/details";
    }

    @DeleteMapping("/address/delete")
    public String deleteAddress(Address address) throws Exception{
        addressService.deleteAddress(address.getId());
        return "buyer/address/delete";
    }
}
