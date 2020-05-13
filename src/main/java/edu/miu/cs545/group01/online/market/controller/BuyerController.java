package edu.miu.cs545.group01.online.market.controller;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.Address;
import edu.miu.cs545.group01.online.market.domain.BillingInfo;
import edu.miu.cs545.group01.online.market.domain.BillingInfoCreditCard;
import edu.miu.cs545.group01.online.market.exception.OrderStatusException;
import edu.miu.cs545.group01.online.market.service.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/buyer")
public class BuyerController extends BaseController {

    @Autowired
    BillingInfoService billingInfoService;
    @Autowired
    AddressService addressService;
    @Autowired
    OrderService orderService;
    @Autowired
    FollowService followService;
    @Autowired
    GainPointService gainPointService;



    @ModelAttribute("myPoints")
    public float getMyPoints(){
        return gainPointService.getFreePoints(getCurrentBuyer());
    }

    @GetMapping("/")
    public String buyer(){
        return "buyer/buyerCabinet";
    }

    @GetMapping("/billing/list")
    public String getBills(Model model) {
        model.addAttribute("bills", billingInfoService.getBillsByBuyer(getCurrentBuyer()));
        return "buyer/billing/list";
    }

    @GetMapping("/billing/bank/update/{billingId}")
    public String getBillingBankAccount(@ModelAttribute("bank") BillingInfoBankAccount bank, @PathVariable("billingId") long billingId, Model model){
        model.addAttribute("bank", billingInfoService.getBilling(getCurrentBuyer(), billingId));
        model.addAttribute("addresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        return "buyer/billing/bank/update";
    }

    @PostMapping("/billing/bank/update/{billingId}")
    public String updateBillingBankAccount(@Valid @PathVariable("billingId") long billingId, BillingInfoBankAccount billingInfoBankAccount, BindingResult bindingResult, Model model) throws NotFoundException{
        if (bindingResult.hasErrors()) {
            model.addAttribute("bank", billingInfoService.getBilling(getCurrentBuyer(), billingId));
            return "buyer/billing/bank/update";
        }
        BillingInfo updateBilling = billingInfoService.updateBankAccount(getCurrentBuyer(), billingId, billingInfoBankAccount);
        return "redirect:/buyer/billing/list";
    }

    @GetMapping("/billing/card/update/{billingId}")
    public String getBillingCreditCard(@ModelAttribute("card") BillingInfoCreditCard card, @PathVariable("billingId") long billingId, Model model){
        model.addAttribute("card", billingInfoService.getBilling(getCurrentBuyer(), billingId));
        model.addAttribute("addresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        return "buyer/billing/card/update";
    }


    @PostMapping("/billing/card/update/{billingId}")
    public String updateBillingCreditCard(@Valid @PathVariable("billingId") long billingId, @ModelAttribute("card") BillingInfoCreditCard card, BillingInfoCreditCard billingInfoCreditCard, BindingResult bindingResult, Model model) throws NotFoundException{
        if (bindingResult.hasErrors()) {
            model.addAttribute("card", billingInfoService.getBilling(getCurrentBuyer(), billingId));
            return "buyer/billing/card/update";
        }
        BillingInfo updateBilling = billingInfoService.updateCreditCard(getCurrentBuyer(),billingId, billingInfoCreditCard);
        return "redirect:/buyer/billing/list";
    }

    @GetMapping("/billing/bank/save")
    public String createBillingBankAccount(@ModelAttribute("bank") BillingInfoBankAccount bank, Model model){
        model.addAttribute("addresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        return "buyer/billing/bank/create";
    }

    @PostMapping("/billing/bank/save")
    public String saveBillingBankAccount(@Valid @ModelAttribute("bank") BillingInfoBankAccount billingInfoBankAccount, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "buyer/billing/bank/create";
        }
        BillingInfo billingInfo1 = billingInfoService.createBankAccount(billingInfoBankAccount, getCurrentBuyer());
        return "redirect:/buyer/billing/list";
    }


    @GetMapping("/billing/card/save")
    public String createBillingCreditCard(@ModelAttribute("card") BillingInfoCreditCard card, Model model){
        model.addAttribute("addresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        return "buyer/billing/card/create";
    }

    @PostMapping("/billing/card/save")
    public String saveBillingCreditCard(@Valid @ModelAttribute("card") BillingInfoCreditCard billingInfoCreditCard, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "buyer/billing/card/create";
        }
        BillingInfo billingInfo1 = billingInfoService.createCreditCard(billingInfoCreditCard, getCurrentBuyer());
        return "redirect:/buyer/billing/list";
    }

    @DeleteMapping("/billing/delete/{billingId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBilling(@PathVariable("billingId") long id)  throws NotFoundException{
        billingInfoService.deleteBilling(getCurrentBuyer(), id);
    }

    @GetMapping("/address/list")
    public String getAddresses(Model model){
        model.addAttribute("addresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        return "buyer/address/list";
    }

    @GetMapping("/address/update/{addressId}")
    public String getAddress(@PathVariable("addressId") long id, Model model){
        model.addAttribute("address",addressService.getMyAddress(getCurrentBuyer(), id));
        return "buyer/address/update";
    }

    @PostMapping("/address/update/{addressId}")
    public String updateAddress(@PathVariable("addressId") long id, Address address) throws NotFoundException{
        Address updateAddress = addressService.updateAddress(getCurrentBuyer(), id, address);
        return "redirect:/buyer/address/list";
    }

    @GetMapping("/address/save")
    public String createAddress(@ModelAttribute("address") Address address) {
        return "buyer/address/create";
    }

    @PostMapping("/address/save")
    public String saveAddress(@Valid Address address, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "buyer/address/create";
        }
        Address address1 = addressService.createAddress(getCurrentBuyer(), address);
        return "redirect:/buyer/address/list";
    }

    @DeleteMapping("/address/delete/{addressId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("addressId") long addressId) throws NotFoundException {
        addressService.deleteAddress(getCurrentBuyer(), addressId);
    }


    @GetMapping("/order/list")
    public String orderList(Model model){
        model.addAttribute("orders", orderService.getMyOrders(getCurrentBuyer()));

        return "buyer/order/list";
    }
    @DeleteMapping("/order/cancel/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelOrder(@PathVariable("orderId") long orderId) throws NotFoundException, OrderStatusException {
        orderService.cancelOrder(getCurrentBuyer(), orderId);
    }

    @PutMapping("/follow-seller/{sellerId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void followSeller(@PathVariable("sellerId") long sellerId) throws NotFoundException {
        followService.followSeller(getCurrentBuyer(), sellerId);
    }
    @PutMapping("/unfollow-seller/{sellerId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void unfollowSeller(@PathVariable("sellerId") long sellerId) throws NotFoundException {
        followService.unfollowSeller(getCurrentBuyer(), sellerId);
    }

    @GetMapping("/checkout")
    public String checkout(@ModelAttribute("checkoutModel") CheckoutModel checkoutModel, Model model){
        model.addAttribute("carts", shoppingCartService.getMyShoppingCarts(getCurrentBuyer()));
        model.addAttribute("myAddresses", addressService.getAddressesByBuyer(getCurrentBuyer()));
        model.addAttribute("myBillingInfos", billingInfoService.getBillsByBuyer(getCurrentBuyer()));
        model.addAttribute("availablePoints", gainPointService.getFreePoints(getCurrentBuyer()));
        return "buyer/cart/checkout";
    }

    @PostMapping("/order/add")
    public String checkout(@Valid CheckoutModel checkoutModel,  BindingResult bindingResult) throws NotFoundException {
        if (bindingResult.hasErrors()) {
            return "buyer/cart/checkout";
        }
        checkoutModel.setBuyer(getCurrentBuyer());
        orderService.createOrder(checkoutModel);
        return "redirect:/buyer/order/list";

    }


    @GetMapping("/cart/list")
    public String myShoppingCart(Model model) {
        model.addAttribute("items", shoppingCartService.getMyShoppingCarts(getCurrentBuyer()));
        return "buyer/cart/list";
    }


    @PostMapping("/cart/add/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToShoppingCart(@PathVariable("productId") long productId) throws NotFoundException {
        shoppingCartService.addShoppingCart(getCurrentBuyer(), productId );
    }
    @DeleteMapping("/cart/delete/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShoppingCart(@PathVariable("cartId") long id) {
        shoppingCartService.deleteShoppingCart(getCurrentBuyer().getId(), id);
    }

    @PostMapping("/cart/set-quantity/{cartId}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setQuantity(@PathVariable("cartId") long cartId, @PathVariable("quantity") int quantity) throws NotFoundException {
        shoppingCartService.setQuantity(getCurrentBuyer().getId(), cartId, quantity);
    }

}
