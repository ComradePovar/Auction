package edu.core.java.auction;


import java.io.*;
import java.text.ParseException;

import edu.core.java.auction.domain.*;
import edu.core.java.auction.repository.*;
import edu.core.java.auction.vo.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO:
// Торги
// Runnable .jar
// В auctionservice возвращаемые и принимаемые типы domain и value поменять местами
public class Main {
    private static ObjectMapper mapper = new ObjectMapper();
    private static AuctionService service = AuctionService.getInstance();
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application has started.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("----------------------------------------------------");
            System.out.println("1. Add entity.");
            System.out.println("2. Delete entity.");
            System.out.println("3. Update entity.");
            System.out.println("4. Show entity.");
            System.out.println("5. Show all entities.");
            System.out.println("6. Exit.");
            System.out.println("----------------------------------------------------");
            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
                switch(choice) {
                    case 1 :
                        addExistedEntity();
                        break;
                    case 2 :
                        deleteEntity();
                        break;
                    case 3 :
                        updateEntity();
                        break;
                    case 4 :
                        showEntity();
                        break;
                    case 5 :
                        showEntities();
                        break;
                    case 6 :
                        logger.info("Application has stopped.");
                        return;
                    default:
                        logger.warn("Incorrect choice");
                }
            } catch (NumberFormatException | ParseException | IOException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    public static void showEntities() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Show products.");
        System.out.println("2. Show buyers.");
        System.out.println("3. Show sellers.");
        System.out.println("4. Show lots.");
        System.out.println("5. Show bids.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        switch(choice){
            case 1:
                for(Product product : service.getAllProducts()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product));
                }
                break;
            case 2:
                for(Buyer buyer : service.getAllBuyers()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(buyer));
                }
                break;
            case 3:
                for(Seller seller : service.getAllSellers()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seller));
                }
                break;
            case 4:
                for(Lot lot : service.getAllLots()){
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lot));
                }
                break;
            case 5:
                for(Bid bid : service.getAllBids()){
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bid));
                }
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void showEntity() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter entity ID: ");
        Long id = Long.parseLong(reader.readLine());
        System.out.println("1. Show product.");
        System.out.println("2. Show buyer.");
        System.out.println("3. Show seller.");
        System.out.println("4. Show lot.");
        System.out.println("5. Show bid.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice2 = Integer.parseInt(reader.readLine());
        switch(choice2){
            case 1 :
                Product product = service.getProductById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(product));
                System.out.println();
                break;
            case 2 :
                Buyer buyer = service.getBuyerById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(buyer));
                break;
            case 3 :
                Seller seller = service.getSellerById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(seller));
                break;
            case 4 :
                Lot lot = service.getLotById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lot));
                break;
            case 5 :
                Bid bid = service.getBidById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bid));
                break;
            case 6 :
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void deleteEntity() throws IOException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Delete product.");
        System.out.println("2. Delete buyer.");
        System.out.println("3. Delete seller.");
        System.out.println("4. Delete lot.");
        System.out.println("5. Delete bid.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        Long id;
        switch(choice){
            case 1 :
                System.out.print("Enter product ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteProductById(id);
                logger.info("Product with id = " + id + " was deleted.");
                break;
            case 2 :
                System.out.print("Enter buyer ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBuyerById(id);
                logger.info("Buyer with id = " + id + " was deleted.");
                break;
            case 3 :
                System.out.print("Enter seller ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteSellerById(id);
                logger.info("Seller with id = " + id + " was deleted.");
                break;
            case 4 :
                System.out.print("Enter lot ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteLotById(id);
                logger.info("LotValueObject with id = " + id + " was deleted.");
                break;
            case 5 :
                System.out.print("Enter bid ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBidById(id);
                logger.info("BidValueObject with id = " + id + " was deleted.");
                break;
            case 6 :
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void updateEntity() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file path: ");
        String filePath = reader.readLine();
        System.out.println("1. Update product.");
        System.out.println("2. Update buyer.");
        System.out.println("3. Update seller.");
        System.out.println("4. Update lot.");
        System.out.println("5. Update bid.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        switch(choice) {
            case 1:
                ProductValueObject product = mapper.readValue(new FileInputStream(filePath), ProductValueObject.class);
                System.out.println(product);

                do {
                    System.out.println("1. Edit title.");
                    System.out.println("2. Edit description.");
                    System.out.println("3. Set owner.");
                    System.out.println("4. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New title: ");
                            product.title = reader.readLine();
                            service.updateProduct(product);
                            logger.info("Product with ID = " + product.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("New description: ");
                            product.description = reader.readLine();
                            service.updateProduct(product);
                            logger.info("Product with ID = " + product.id + " was updated.");
                            break;
                        case 3:
                            System.out.print("New owner ID: ");
                            product.ownerId = Long.parseLong(reader.readLine());
                            service.updateProduct(product);
                            logger.info("Product with ID = " + product.id + " was updated.");
                            break;
                        case 4:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 4);

                mapper.writeValue(new FileOutputStream(filePath), product);
                logger.info("File with product ID = " + product.id + " was rewrote.");
                break;
            case 2:
                BuyerValueObject buyer = mapper.readValue(new FileInputStream(filePath), BuyerValueObject.class);
                System.out.println(buyer);

                do {
                    System.out.println("1. Edit name.");
                    System.out.println("2. Set money amount.");
                    System.out.println("3. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New name: ");
                            buyer.name = reader.readLine();
                            service.updateBuyer(buyer);
                            logger.info("Buyer with ID = " + buyer.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("Amount: ");
                            buyer.accountBalance += Double.parseDouble(reader.readLine());
                            service.updateBuyer(buyer);
                            logger.info("Buyer with ID = " + buyer.id + " was updated.");
                            break;
                        case 3:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 3);

                mapper.writeValue(new FileOutputStream(filePath), buyer);
                logger.info("File with buyer ID = " + buyer.id + " was rewrote.");
                break;
            case 3:
                SellerValueObject seller = mapper.readValue(new FileInputStream(filePath), SellerValueObject.class);
                System.out.println(seller);

                do {
                    System.out.println("1. Edit name.");
                    System.out.println("2. Set money amount.");
                    System.out.println("3. Set commission percentage.");
                    System.out.println("4. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New name: ");
                            seller.name = reader.readLine();
                            service.updateSeller(seller);
                            logger.info("Seller with ID = " + seller.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("Amount: ");
                            seller.accountBalance = Double.parseDouble(reader.readLine());
                            service.updateSeller(seller);
                            logger.info("Seller with ID = " + seller.id + " was updated.");
                            break;
                        case 3:
                            System.out.print("New commission percentage: ");
                            seller.commissionPercentage = Double.parseDouble(reader.readLine());
                            service.updateSeller(seller);
                            logger.info("Seller with ID = " + seller.id + " was updated.");
                            break;
                        case 4:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 4);

                mapper.writeValue(new FileOutputStream(filePath), seller);
                logger.info("File with seller ID = " + seller.id + " was rewrote.");
                break;
            case 4:
                LotValueObject lot = mapper.readValue(new FileInputStream(filePath), LotValueObject.class);
                System.out.println(lot);

                do {
                    System.out.println("1. Edit end date.");
                    System.out.println("2. Replace product id.");
                    System.out.println("3. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new end date: ");
                            String newEndDate = reader.readLine();
                            lot.endDate = service.getDateFormat().parse(newEndDate);
                            service.updateLot(lot);
                            logger.info("Lot with ID = " + lot.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("Enter new product id: ");
                            lot.productId = Long.parseLong(reader.readLine());
                            service.updateLot(lot);
                            logger.info("Lot with ID = " + lot.id + " was updated.");
                            break;
                        case 3:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while(choice != 3);

                mapper.writeValue(new FileOutputStream(filePath), lot);
                logger.info("File with lot ID = " + lot.id + " was rewrote.");
                break;
            case 5:
                BidValueObject bid = mapper.readValue(new FileInputStream(filePath), BidValueObject.class);
                System.out.println(bid);

                do{
                    System.out.println("1. Edit bid amount: ");
                    System.out.println("2. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch(choice){
                        case 1:
                            System.out.print("Enter new bid amount: ");
                            bid.amount = Double.parseDouble(reader.readLine());
                            service.updateBid(bid);
                            logger.info("Bid with ID = " + bid.id + " was updated.");
                            break;
                        case 2:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 2);

                mapper.writeValue(new FileOutputStream(filePath), bid);
                logger.info("File with bid ID = " + bid.id + " was rewrote.");
                break;
            case 6:
                break;
        }
    }
    private static void addExistedEntity() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file path: ");
        String filePath = reader.readLine();
        System.out.println("Enter type of entity (1-Product, 2-Buyer, 3-Seller, 4-Lot, 5-Bid):");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
                ProductValueObject productValueObject =
                        mapper.readValue(new FileInputStream(filePath), ProductValueObject.class);
                ProductRepository productRepository = service.getProductRepository();
                if (productRepository.contains(productValueObject.id)){
                    logger.warn("Product with id = " + productValueObject.id + " already exists.");
                    return;
                }
                productRepository.add(productValueObject);
                productRepository.incMaxId();
                logger.info("Product with id = " + productValueObject.id + " was added to repository.");
                break;
            case 2:
                BuyerValueObject buyerValueObject =
                        mapper.readValue(new FileInputStream(filePath), BuyerValueObject.class);
                BuyerRepository buyerRepository = service.getBuyerRepository();
                if (buyerRepository.contains(buyerValueObject.id)){
                    logger.warn("Buyer with id = " + buyerValueObject.id + " already exists.");
                    return;
                }
                buyerRepository.add(buyerValueObject);
                buyerRepository.incMaxId();
                logger.info("Buyer with id = " + buyerValueObject.id + " was added to repository.");
                break;
            case 3:
                SellerValueObject sellerValueObject =
                        mapper.readValue(new FileInputStream(filePath), SellerValueObject.class);
                SellerRepository sellerRepository = service.getSellerRepository();
                if (sellerRepository.contains(sellerValueObject.id)){
                    logger.warn("Buyer with id = " + sellerValueObject.id + " already exists.");
                    return;
                }
                sellerRepository.add(sellerValueObject);
                sellerRepository.incMaxId();
                logger.info("Seller with id = " + sellerValueObject.id + " was added to repository.");
                break;
            case 4:
                LotValueObject lotValueObject =
                        mapper.readValue(new FileInputStream(filePath), LotValueObject.class);
                LotRepository lotRepository = service.getLotRepository();
                if (lotRepository.contains(lotValueObject.id)){
                    logger.warn("Lot with id = " + lotValueObject.id + " already exists.");
                    return;
                }
                lotRepository.add(lotValueObject);
                lotRepository.incMaxId();
                logger.info("Lot with id = " + lotValueObject.id + " was added to repository.");
                break;
            case 5:
                BidValueObject bidValueObject =
                        mapper.readValue(new FileInputStream(filePath), BidValueObject.class);
                BidRepository bidRepository = service.getBidRepository();
                if (bidRepository.contains(bidValueObject.id)){
                    logger.warn("Lot with id = " + bidValueObject.id + " already exists.");
                    return;
                }
                service.createBid(bidValueObject.id, bidValueObject.buyerId, bidValueObject.lotId, bidValueObject.amount);
                logger.info("Bid with id = " + bidValueObject.id + " was added to repository.");
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }
}
