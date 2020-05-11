package edu.miu.cs545.group01.online.market;

import edu.miu.cs545.group01.online.market.domain.*;
import edu.miu.cs545.group01.online.market.domain.enums.*;
import edu.miu.cs545.group01.online.market.helper.Helper;
import edu.miu.cs545.group01.online.market.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
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
			BillingInfoRepository billingInfoRepository,
			OrderProductRepository orderProductRepository,
			ReviewRepository reviewRepository,
			ShoppingCartRepository shoppingCartRepository,
			FollowsRepository followsRepository
	){
		return args -> {
			Admin admin = new Admin("Nurlan Kustutinov","nurlan985@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			admin = adminRepository.save(admin);
			Seller seller = new Seller("Girma","girma@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			seller = sellerRepository.save(seller);
			Seller seller2 = new Seller("Jirom","jirom@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			seller2 = sellerRepository.save(seller2);
			Seller mike = new Seller("Mike","Mike@gmail.com", passwordEncoder.encode("123"), UserStatus.PENDING);
			mike = sellerRepository.save(mike);
			Seller john = new Seller("John","john@gmail.com", passwordEncoder.encode("123"), UserStatus.PENDING);
			john = sellerRepository.save(john);
			Seller sam = new Seller("Sam","sam@gmail.com", passwordEncoder.encode("123"), UserStatus.PENDING);
			sam = sellerRepository.save(sam);
			Buyer buyerYafet = new Buyer("Yafet", "yafet@gmail.com", passwordEncoder.encode("123456"), UserStatus.ACTIVE);
			buyerYafet = buyerRepository.save(buyerYafet);
			Buyer buyerRobel = new Buyer("Robel", "Robel@gmail.com", passwordEncoder.encode("1234567"), UserStatus.ACTIVE);
			buyerRobel = buyerRepository.save(buyerRobel);
			Buyer buyerjirom = new Buyer("Jirom", "Jirom@gmail.com", passwordEncoder.encode("123"), UserStatus.ACTIVE);
			buyerjirom = buyerRepository.save(buyerjirom);


			Category toys = new Category("Toys & Hobbies");
			toys = categoryRepository.save(toys);

			Category fashion = new Category("Fashion");
			fashion = categoryRepository.save(fashion);

			Category category = new Category("Electronic Devices");
			category = categoryRepository.save(category);


			Address yafetaddress = new Address("52557", "2000 N", "fairfield","Iowa","644-000-789","USA",buyerYafet,AddressStatus.ACTIVE);
			yafetaddress = addressRepository.save(yafetaddress);
			Address robelAddress = new Address("52557", "2000 N", "fairfield","Iowa","234-567-789","USA",buyerRobel,AddressStatus.ACTIVE);
			robelAddress = addressRepository.save(robelAddress);
			Address jiromAddress = new Address("52557", "4th street", "Fairfield", "Iowa", "644-333-3333", "USA", buyerjirom, AddressStatus.ACTIVE );
			jiromAddress = addressRepository.save(jiromAddress);


			BillingInfo yafetbillingInfo = new BillingInfo(123456, "000-111-222", Helper.getDate(2024,12,01, 19), "123", yafetaddress, BillingInfoStatus.ACTIVE );
			yafetbillingInfo = billingInfoRepository.save(yafetbillingInfo);
			BillingInfo robelbillingInfo = new BillingInfo(9876, "111-111-222", Helper.getDate(2024,11,01, 19), "123", robelAddress, BillingInfoStatus.ACTIVE );
			robelbillingInfo = billingInfoRepository.save(robelbillingInfo);
			BillingInfo jirombillingInfo = new BillingInfo(100876, "323-111-222", Helper.getDate(2024,11,01, 19), "123", jiromAddress, BillingInfoStatus.ACTIVE );
			jirombillingInfo = billingInfoRepository.save(jirombillingInfo);

			Product productIphone = new Product("iPhone 11",category, 1100, ProductStatus.ACTIVE, "iphone.jpg", "Brand new", seller );
			productIphone = productRepository.save(productIphone);
			Product productLaptop = new Product("mac book",category, 1800, ProductStatus.SOLDOUT, "laptop1.jpg", "Old Version", seller2 );
			productLaptop = productRepository.save(productLaptop);
			Product productAserLaptop =new Product ( "Aser laptop" , category, 3500, ProductStatus.ACTIVE,"laptop-aser.jpg","Brand new", seller);
			productAserLaptop = productRepository.save(productAserLaptop);
			Product converseshoes =new Product ( "converse shoes" , fashion, 60, ProductStatus.ACTIVE,"converseshoes.jpg","Brand new", seller);
			converseshoes = productRepository.save(converseshoes);

			Product applewatch = new Product("Apple Watch",category, 450, ProductStatus.ACTIVE, "applewatch.jpg", "Brand new", seller );
			applewatch = productRepository.save(applewatch);
			Product samsungNote10 = new Product("Samsung Note 10",category, 1200, ProductStatus.ACTIVE, "samsung10plus.jpg", "Brand new", seller );
			samsungNote10 = productRepository.save(samsungNote10);
			Product kidscar = new Product("Kids Car Toy",toys, 3200, ProductStatus.ACTIVE, "kidscar.jpg", "Brand new", seller2 );
			kidscar = productRepository.save(kidscar);
			Product ball = new Product("Balls",toys, 70, ProductStatus.ACTIVE, "ball.jpg", "All Sport Ball", seller );
			ball = productRepository.save(ball);
			Order order1 = new Order(Helper.getDate(2020, 5, 10, 13, 50, 8), OrderStatus.CREATED,buyerYafet,seller, yafetaddress, yafetbillingInfo, Helper.getDate(2020,05,1,13,00,00),  Helper.getDate(2020,05,16,13,00,00));
			order1 = orderRepository.save(order1);
			Order order2 = new Order(Helper.getDate(2020,05, 9,14,34,44),OrderStatus.DELIVERED, buyerRobel,seller,robelAddress,robelbillingInfo, Helper.getDate(2020,05, 12,14,34,44),Helper.getDate(2020,05, 15,14,34,44) );
			order2 = orderRepository.save(order2);
			Order order3 = new Order(Helper.getDate(2020,16,04,9,45,22),OrderStatus.SHIPPED,buyerYafet,seller,yafetaddress, yafetbillingInfo,Helper.getDate(2020,05,16,13,00,00), Helper.getDate(2020,05,16,16,00,00));
			order3 = orderRepository.save(order3);
			Order order4 = new Order(Helper.getDate(2020,05, 10,14,34,44),OrderStatus.DELIVERED, buyerRobel,seller,robelAddress,robelbillingInfo, Helper.getDate(2020,05, 12,14,34,44),Helper.getDate(2020,05, 15,14,34,44) );
			order4 = orderRepository.save(order4);
			Order order5 = new Order(Helper.getDate(2020,05, 10,14,34,44),OrderStatus.DELIVERED, buyerjirom,mike,jiromAddress,jirombillingInfo, Helper.getDate(2020,05, 12,14,34,44),Helper.getDate(2020,05, 15,14,34,44) );
			order5 = orderRepository.save(order5);

			OrderProduct orderProduct1 = new OrderProduct(order1,productIphone,1,0,1100);
			orderProduct1 = orderProductRepository.save(orderProduct1);
			OrderProduct orderProduct2 = new OrderProduct(order2,productLaptop,1,0,1800);
			orderProduct2 = orderProductRepository.save(orderProduct2);
			OrderProduct orderProduct3 = new OrderProduct(order3,productAserLaptop,1,0,3500);
			orderProduct3 = orderProductRepository.save(orderProduct3);
			OrderProduct orderProduct4 = new OrderProduct(order4,productIphone,2,0,2200);
			orderProduct4 = orderProductRepository.save(orderProduct4);
			OrderProduct orderProduct5 = new OrderProduct(order5,applewatch,2,0,450);
			orderProduct5 = orderProductRepository.save(orderProduct5);

			Review review1 = new Review(orderProduct4, ReviewStatus.POSTED, buyerRobel,Helper.getDate(2020,06,10,13,00,00),5,"Excellent Product", Helper.getDate(2020,06,16,13,00,00));
			review1 = reviewRepository.save(review1);
			Review review2 = new Review(orderProduct2, ReviewStatus.CREATED, buyerRobel,Helper.getDate(2020,05,19,14,00,00),4,"Good Product", Helper.getDate(2020,05,20,14,00,00));
			review2 = reviewRepository.save(review2);
			Review review3 = new Review(orderProduct5, ReviewStatus.POSTED, buyerYafet,Helper.getDate(2020,06,26,13,00,00),5,"Very Good Product", Helper.getDate(2020,06,26,13,00,00));
			review3 = reviewRepository.save(review3);

			ShoppingCart shoppingCart1 = new ShoppingCart(buyerYafet,productIphone,2,Helper.getDate(2020, 5, 11, 13, 50, 8));
			shoppingCart1 = shoppingCartRepository.save(shoppingCart1);
			ShoppingCart shoppingCart2 = new ShoppingCart(buyerRobel,productLaptop,1,Helper.getDate(2020,05, 12,14,34,44));
			shoppingCart2 = shoppingCartRepository.save(shoppingCart2);
			ShoppingCart shoppingCart3 = new ShoppingCart(buyerYafet,productAserLaptop,1,Helper.getDate(2020,16,04,11,45,22));
			shoppingCart3 = shoppingCartRepository.save(shoppingCart3);

			Follows follows1 = new Follows(seller,buyerRobel);
			follows1 = followsRepository.save(follows1);
		};
	}
}
