package edu.core.java.auction;

import edu.core.java.auction.domain.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
// TODO:
// Добавить обновление
// Исправить майн
// ПЕРЕПРОВЕРИТЬ ВСЕ
// Добавить JSON и лог
public class Main {
    private static HashSet<Seller> sellers = new HashSet<>();
    private static HashSet<Auctioneer> auctioneers = new HashSet<>();
    private static HashSet<Buyer> buyers = new HashSet<>();
    private static HashSet<Lot> lots = new HashSet<>();
    private static HashSet<Bid> bids = new HashSet<>();
    private static HashSet<Product> products = new HashSet<>();

    public static void main(String[] args) throws Exception{
        AuctionService service = AuctionService.getInstance();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            System.out.println("----------------------------------------------------");
            System.out.println("1. Create entity.");
            System.out.println("2. Delete entity.");
            System.out.println("3. Update entity.");
            System.out.println("4. Show entities.");
            System.out.println("5. Exit.");
            System.out.println("----------------------------------------------------");

            int choice = Integer.parseInt(reader.readLine());
            switch(choice) {
                case 1 :
                    System.out.println("1. Create auctioneer.");
                    System.out.println("2. Create product.");
                    System.out.println("3. Create buyer.");
                    System.out.println("4. Create seller.");
                    System.out.println("5. Create lot.");
                    System.out.println("6. Create bid.");
                    System.out.println("7. Main menu.");
                    System.out.println("----------------------------------------------------");
                    choice = Integer.parseInt(reader.readLine());
                    createEntity(choice);
                    break;
                case 2 :
                    System.out.println("1. Delete auctioneer.");
                    System.out.println("2. Delete product.");
                    System.out.println("3. Delete buyer.");
                    System.out.println("4. Delete seller.");
                    System.out.println("5. Delete lot.");
                    System.out.println("6. Delete bid.");
                    System.out.println("7. Main menu.");
                    System.out.println("----------------------------------------------------");
                    choice = Integer.parseInt(reader.readLine());
                    deleteEntity(choice);
                    break;
                case 3 :
                    System.out.println("1. Update auctioneer.");
                    System.out.println("2. Update product.");
                    System.out.println("3. Update buyer.");
                    System.out.println("4. Update seller.");
                    System.out.println("5. Update lot.");
                    System.out.println("6. Update bid.");
                    System.out.println("7. Main menu.");
                    System.out.println("----------------------------------------------------");
                    Long id;
                    choice = Integer.parseInt(reader.readLine());
                    switch(choice){
                        case 1 :
                            System.out.print("Enter auctioneer ID: ");
                            id = Long.parseLong(reader.readLine());
                            Auctioneer auctioneer = service.getAuctioneerById(id);
                            System.out.println(auctioneer.getId());
                            System.out.println(auctioneer.getName());
                            System.out.print("LotValueObject IDs: ");

                            for (Lot lot : auctioneer.getLots()){
                                System.out.print(lot.getId() + " ");
                            }

                            do {
                                System.out.println("1. Edit name.");
                                System.out.println("2. Delete lot.");
                                System.out.println("3. Main menu.");
                                choice = Integer.parseInt(reader.readLine());
                                switch (choice) {
                                    case 1:
                                        System.out.print("New name: ");
                                        auctioneer.setName(reader.readLine());
                                        service.updateAuctioneer(auctioneer);
                                        System.out.println("Auctioneer with ID = " + id + " was updated.");
                                        break;
                                    case 2:
                                        System.out.print("Lot id: ");
                                        Long lotId = Long.parseLong(reader.readLine());
                                        service.deleteLotById(lotId);
                                        System.out.println("Lot with ID = " + id + " was deleted.");
                                        break;
                                }
                            } while(choice != 3);
                        case 2 :
                            System.out.print("Enter product ID: ");
                            id = Long.parseLong(reader.readLine());
                            Product simpleProduct = service.getProductById(id);
                            System.out.println(simpleProduct.getId());
                            System.out.println(simpleProduct.getTitle());
                            System.out.println(simpleProduct.getDescription());
                            System.out.println("Owner ID: " + simpleProduct.getOwner().getId());
                            System.out.println("Seller price: " + simpleProduct.getSellerPrice());

                            do {
                                System.out.println("1. Edit title.");
                                System.out.println("2. Edit description.");
                                System.out.println("3. Set owner.");
                                System.out.println("4. Set seller price.");
                                System.out.println("5. Main menu.");
                                choice = Integer.parseInt(reader.readLine());
                                switch (choice) {
                                    case 1:
                                        System.out.print("New name: ");
                                        auctioneer.setName(reader.readLine());
                                        service.updateAuctioneer(auctioneer);
                                        System.out.println("Auctioneer with ID = " + id + " was updated.");
                                        break;
                                    case 2:
                                        System.out.print("Lot id: ");
                                        Long lotId = Long.parseLong(reader.readLine());
                                        service.deleteLotById(lotId);
                                        System.out.println("Lot with ID = " + id + " was deleted.");
                                        break;
                                    case 3:
                                    case 4:
                                }
                            } while(choice != 5);
                            break;
                        case 3 :
                            System.out.print("Enter buyer ID: ");
                            id = Long.parseLong(reader.readLine());
                            Buyer buyer = service.getBuyerById(id);
                            System.out.println(buyer.getId());
                            System.out.println(buyer.getName());
                            System.out.println(buyer.getAccountBalance());
                            System.out.println("BidValueObject IDs: ");
                            for (Bid bid : buyer.getBids()){
                                System.out.print(bid.getId() + " ");
                            }
                            break;
                        case 4 :
                            System.out.print("Enter seller ID: ");
                            id = Long.parseLong(reader.readLine());
                            Seller seller = service.getSellerById(id);
                            System.out.println(seller.getId());
                            System.out.println(seller.getName());
                            System.out.println(seller.getAccountBalance());
                            System.out.println(seller.getComissionPercentage());
                            System.out.println("Product IDs: ");
                            for (Product product : seller.getProducts()){
                                System.out.print(product.getId() + " ");
                            }
                            break;
                        case 5 :
                            System.out.print("Enter lot ID: ");
                            id = Long.parseLong(reader.readLine());
                            Lot lot = service.getLotById(id);
                            System.out.println(lot.getId());
                            System.out.println("Start date: " + lot.getStartDate());
                            System.out.println("End date: " + lot.getEndDate());
                            System.out.println("Current bid ID: " + lot.getCurrentBid().getId());
                            System.out.println("Auctioneer ID: " + lot.getAuctioneer().getId());
                            System.out.println("Product ID: " + lot.getProduct().getId());
                            break;
                        case 6 :
                            System.out.print("Enter bid ID: ");
                            id = Long.parseLong(reader.readLine());
                            Bid bid = service.getBidById(id);
                            System.out.println(bid.getId());
                            System.out.println("Buyer ID: " + bid.getBuyer().getId());
                            System.out.println(bid.getBidAmount());
                            break;
                        case 7 :
                            break;
                    break;
                case 4 :
                    System.out.println("1. Show auctioneer.");
                    System.out.println("2. Show product.");
                    System.out.println("3. Show buyer.");
                    System.out.println("4. Show seller.");
                    System.out.println("5. Show lot.");
                    System.out.println("6. Show bid.");
                    System.out.println("7. Main menu.");
                    System.out.println("----------------------------------------------------");
                    choice = Integer.parseInt(reader.readLine());
                    showEntity(choice);
                    break;
                case 5 :
                    return;
            }
        }

        Timer timer = new Timer();
       // timer.schedule(new Checker(auctioneer, seller, buyer1, buyer2, product, lot), 5000);
    }

    public static void showEntity(int choice) throws Exception{
        Long id;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AuctionService service = AuctionService.getInstance();
        switch(choice){
            case 1 :
                System.out.print("Enter auctioneer ID: ");
                id = Long.parseLong(reader.readLine());
                Auctioneer auctioneer = service.getAuctioneerById(id);
                System.out.println(auctioneer.getId());
                System.out.println(auctioneer.getName());
                System.out.print("LotValueObject IDs: ");
                for (Lot lot : auctioneer.getLots()){
                    System.out.print(lot.getId() + " ");
                }
                break;
            case 2 :
                System.out.print("Enter product ID: ");
                id = Long.parseLong(reader.readLine());
                Product simpleProduct = service.getProductById(id);
                System.out.println(simpleProduct.getId());
                System.out.println(simpleProduct.getTitle());
                System.out.println(simpleProduct.getDescription());
                System.out.println("Owner ID: " + simpleProduct.getOwner().getId());
                System.out.println("Seller price: " + simpleProduct.getSellerPrice());
                break;
            case 3 :
                System.out.print("Enter buyer ID: ");
                id = Long.parseLong(reader.readLine());
                Buyer buyer = service.getBuyerById(id);
                System.out.println(buyer.getId());
                System.out.println(buyer.getName());
                System.out.println(buyer.getAccountBalance());
                System.out.println("BidValueObject IDs: ");
                for (Bid bid : buyer.getBids()){
                    System.out.print(bid.getId() + " ");
                }
                break;
            case 4 :
                System.out.print("Enter seller ID: ");
                id = Long.parseLong(reader.readLine());
                Seller seller = service.getSellerById(id);
                System.out.println(seller.getId());
                System.out.println(seller.getName());
                System.out.println(seller.getAccountBalance());
                System.out.println(seller.getComissionPercentage());
                System.out.println("Product IDs: ");
                for (Product product : seller.getProducts()){
                    System.out.print(product.getId() + " ");
                }
                break;
            case 5 :
                System.out.print("Enter lot ID: ");
                id = Long.parseLong(reader.readLine());
                Lot lot = service.getLotById(id);
                System.out.println(lot.getId());
                System.out.println("Start date: " + lot.getStartDate());
                System.out.println("End date: " + lot.getEndDate());
                System.out.println("Current bid ID: " + lot.getCurrentBid().getId());
                System.out.println("Auctioneer ID: " + lot.getAuctioneer().getId());
                System.out.println("Product ID: " + lot.getProduct().getId());
                break;
            case 6 :
                System.out.print("Enter bid ID: ");
                id = Long.parseLong(reader.readLine());
                Bid bid = service.getBidById(id);
                System.out.println(bid.getId());
                System.out.println("Buyer ID: " + bid.getBuyer().getId());
                System.out.println(bid.getBidAmount());
                break;
            case 7 :
                break;
        }
    }

    public static void deleteEntity(int choice) throws Exception{
        Long id;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AuctionService service = AuctionService.getInstance();
        switch(choice){
            case 1 :
                System.out.print("Enter auctioneer ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteAuctioneerById(id);
                System.out.println("Auctioneer with id = " + id + " was deleted.");
                break;
            case 2 :
                System.out.print("Enter product ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteProductById(id);
                System.out.println("Product with id = " + id + " was deleted.");
                break;
            case 3 :
                System.out.print("Enter buyer ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBuyerById(id);
                System.out.println("Buyer with id = " + id + " was deleted.");
                break;
            case 4 :
                System.out.print("Enter seller ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteSellerById(id);
                System.out.println("Seller with id = " + id + " was deleted.");
                break;
            case 5 :
                System.out.print("Enter lot ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteLotById(id);
                System.out.println("LotValueObject with id = " + id + " was deleted.");
                break;
            case 6 :
                System.out.print("Enter bid ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBidById(id);
                System.out.println("BidValueObject with id = " + id + " was deleted.");
                break;
            case 7 :
                break;
        }
    }

    public static void createEntity(int choice) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        AuctionService service = AuctionService.getInstance();
        switch(choice){
            case 1 :
                System.out.print("Name: ");
                String aucName = reader.readLine();
                Auctioneer auctioneer = service.createAuctioneer(aucName);
                auctioneers.add(auctioneer);
                System.out.println("Auctioneer with id = " + auctioneer.getId() + " was created.");
                break;
            case 2 :
                System.out.print("Title: ");
                String prodTitle = reader.readLine();
                System.out.print("Description: ");
                String prodDescr = reader.readLine();
                System.out.print("Owner ID: ");
                String prodOwnerId = reader.readLine();
                System.out.print("Seller price: ");
                String prodSellerPrice = reader.readLine();
                Product product = service.createProduct(
                        prodTitle, prodDescr, Long.parseLong(prodOwnerId), Double.parseDouble(prodSellerPrice));
                products.add(product);
                System.out.println("Product with id = " + product.getId() + " was created.");
                break;
            case 3 :
                System.out.println("Name: ");
                String buyerName = reader.readLine();
                System.out.println("Account balance: ");
                String buyerAccountBalance = reader.readLine();
                Buyer buyer = service.createBuyer(buyerName, Double.parseDouble(buyerAccountBalance));
                buyers.add(buyer);
                System.out.println("Buyer with id = " + buyer.getId() + " was created.");
                break;
            case 4 :
                System.out.println("Name: ");
                String sellerName = reader.readLine();
                System.out.println("Account balance: ");
                String sellerAccountBalance = reader.readLine();
                System.out.println("Comission percentage: ");
                String comissionPercentage = reader.readLine();
                Seller seller = service.createSeller(
                        sellerName, Double.parseDouble(sellerAccountBalance), Double.parseDouble(comissionPercentage));
                sellers.add(seller);
                System.out.println("Seller with id = " + seller.getId() + " was created.");
                break;
            case 5 :
                System.out.println("Start price: ");
                String lotStartPrice = reader.readLine();
                System.out.println("End date: ");
                String lotEndDate = reader.readLine();
                System.out.println("Product ID: ");
                String lotProductId = reader.readLine();
                System.out.println("Auctioneer ID: ");
                String lotAuctioneerId = reader.readLine();
                Lot lot = service.createLot(
                        Double.parseDouble(lotStartPrice), service.getDateFormat().parse(lotEndDate),
                        Long.parseLong(lotProductId), Long.parseLong(lotAuctioneerId));
                lots.add(lot);
                System.out.println("LotValueObject with id = " + lot.getId() + " was created.");
                break;
            case 6 :
                System.out.println("Buyer ID: ");
                String bidBuyerId = reader.readLine();
                System.out.println("BidValueObject amount: ");
                String bidAmount = reader.readLine();
                System.out.println("LotValueObject ID: ");
                String bidLotId = reader.readLine();
                Bid bid = service.createBid(
                        Long.parseLong(bidBuyerId), Long.parseLong(bidLotId), Double.parseDouble(bidAmount));
                bids.add(bid);
                System.out.println("BidValueObject with id = " + bid.getId() + " was created.");
                break;
            case 7 :
                break;
        }
    }

    public static class Checker extends TimerTask{
        Auctioneer auctioneer;
        Seller seller;
        Buyer buyer1;
        Buyer buyer2;
        Product product;
        Lot lot;

        public Checker(Auctioneer auc, Seller sel, Buyer buy1, Buyer buy2, Product pro, Lot lot){
            this.auctioneer = auc;
            this.seller = sel;
            this.buyer1 = buy1;
            this.buyer2 = buy2;
            this.product = pro;
            this.lot = lot;
        }
        @Override
        public void run() {
            System.out.println("Seller balance: " + seller.getAccountBalance());
            System.out.println("Buyer1 balance: " + buyer1.getAccountBalance());
            System.out.println("Buyer2 balance: " + buyer2.getAccountBalance());
        }
    }

}
