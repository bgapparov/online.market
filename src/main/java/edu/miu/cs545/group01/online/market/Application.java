package edu.miu.cs545.group01.online.market;

import edu.miu.cs545.group01.online.market.config.SecurityConfig;
import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.*;
import edu.miu.cs545.group01.online.market.helper.Helper;
import edu.miu.cs545.group01.online.market.repository.*;
import edu.miu.cs545.group01.online.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner demo(
			AdminRepository adminRepository,
			SellerRepository sellerRepository,
			BuyerRepository buyerRepository,
			CategoryRepository categoryRepository,
			ProductRepository productRepository,
			OrderRepository orderRepository,
			AddressRepository addressRepository,
			BillingInfoRepository billingInfoRepository
	){
		return args -> {
			Admin admin = new Admin("Nurlan Kustutinov","nurlan985@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			admin = adminRepository.save(admin);
			Seller seller = new Seller("Girma","girma@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			seller = sellerRepository.save(seller);
			Seller seller2 = new Seller("Jirom","jirom@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			seller2 = sellerRepository.save(seller2);
			Seller sellerPending = new Seller("Big Seller","pending@gmail.com", passwordEncoder.encode("123"), UserStatus.PENDING);
			sellerPending = sellerRepository.save(sellerPending);
			Buyer buyer = new Buyer("Yafet", "yafet@gmail.com", passwordEncoder.encode("123456"), UserStatus.ACTIVE);
			buyer = buyerRepository.save(buyer);
			Buyer buyer2 = new Buyer("Robel", "Robel@gmail.com", passwordEncoder.encode("1234567"), UserStatus.ACTIVE);
			buyer2 = buyerRepository.save(buyer2);

			Category category = new Category("Electronic Devices");
			category = categoryRepository.save(category);

			Address yafetaddress = new Address("4th street", "Fairfield", "Iowa", "644-000-1111","USA",buyer,AddressStatus.ACTIVE);
			yafetaddress = addressRepository.save(yafetaddress);
			Address robelAddress = new Address("52557", "2000 N", "fairfield","Iowa","234-567-789","USA",buyer2,AddressStatus.ACTIVE);
			robelAddress = addressRepository.save(robelAddress);
			BillingInfo yafetbillingInfo = new BillingInfo(123456, "000-111-222", Helper.getDate(2024,12,01, 19), "123", yafetaddress, BIllingInfoStatus.ACTIVE );
			yafetbillingInfo = billingInfoRepository.save(yafetbillingInfo);
			BillingInfo robelbillingInfo = new BillingInfo(9876, "111-111-222", Helper.getDate(2024,11,01, 19), "123", robelAddress, BIllingInfoStatus.ACTIVE );
			yafetbillingInfo = billingInfoRepository.save(yafetbillingInfo);

			Product productIphone = new Product("iPhone 11",category, 1100, ProductStatus.ACTIVE, "iphone.jpg", "Brand new", seller );
			productIphone = productRepository.save(productIphone);
			Product productLaptop = new Product("mac book",category, 1800, ProductStatus.SOLDOUT, "laptop1.jpg", "Old Version", seller2 );
			productLaptop = productRepository.save(productLaptop);
			Product productAserLaptop =new Product ( "Aser laptop" , category, 3500, ProductStatus.ACTIVE,"laptop-aser.jpg","Brand new", seller);
			productAserLaptop = productRepository.save(productAserLaptop);


			Order order1 = new Order(Helper.getDate(2020, 5, 10, 13, 50, 8), OrderStatus.CREATED,buyer,seller, yafetaddress, yafetbillingInfo, Helper.getDate(2020,05,1,13,00,00),  Helper.getDate(2020,05,16,13,00,00),1);
			order1 = orderRepository.save(order1);
			Order order2 = new Order(Helper.getDate(2020,05, 9,14,34,44),OrderStatus.DELIVERED, buyer2,seller,robelAddress,robelbillingInfo, Helper.getDate(2020,05, 12,14,34,44),Helper.getDate(2020,05, 15,14,34,44) ,2 );
			order2 = orderRepository.save(order2);
			Order order3 = new Order(Helper.getDate(2020,16,04,9,45,22),OrderStatus.SHIPPED,buyer,seller,yafetaddress, yafetbillingInfo,Helper.getDate(2020,05,16,13,00,00), Helper.getDate(2020,05,16,16,00,00) ,1);
			order3 = orderRepository.save(order3);


		};
	}
}
