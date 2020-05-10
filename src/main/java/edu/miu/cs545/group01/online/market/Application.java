package edu.miu.cs545.group01.online.market;

import edu.miu.cs545.group01.online.market.config.SecurityConfig;
import edu.miu.cs545.group01.online.market.domain.Admin;
import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.Seller;
import edu.miu.cs545.group01.online.market.domain.enums.UserStatus;
import edu.miu.cs545.group01.online.market.repository.AdminRepository;
import edu.miu.cs545.group01.online.market.repository.BuyerRepository;
import edu.miu.cs545.group01.online.market.repository.SellerRepository;
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
			BuyerRepository buyerRepository){
		return args -> {
			Admin admin = new Admin("Nurlan Kustutinov","nurlan985@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			admin = adminRepository.save(admin);
			Seller seller = new Seller("Girma","girma@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			seller = sellerRepository.save(seller);
			Seller sellerPending = new Seller("Big Seller","pending@gmail.com", passwordEncoder.encode("123"), UserStatus.PENDING);
			sellerPending = sellerRepository.save(sellerPending);
			Buyer buyer = new Buyer("Yafet", "yafet@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			buyer = buyerRepository.save(buyer);
		};
	}
}
