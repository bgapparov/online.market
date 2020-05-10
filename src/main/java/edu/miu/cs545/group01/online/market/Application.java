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



			Product productIphone = new Product("iPhone 11",category, 1100, ProductStatus.ACTIVE, "iphone.jpg", "Brand new", seller );
			productIphone = productRepository.save(productIphone);
			Product productLaptop = new Product("mac book",category, 1800, ProductStatus.SOLDOUT, "laptop1.jpg", "Old Version", seller2 );
			productLaptop = productRepository.save(productLaptop);
			Product productAserLaptop =new Product ( "Aser laptop" , category, 3500, ProductStatus.ACTIVE,"laptop-aser.jpg","Brand new", seller);
			productAserLaptop = productRepository.save(productAserLaptop);





		};
	}
}
