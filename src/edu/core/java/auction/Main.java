package edu.core.java.auction;

import edu.core.java.auction.domain.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
// TODO:
// Добавить обновление
// Исправить сервис и майн
// ПЕРЕПРОВЕРИТЬ ВСЕ
// Добавить JSON и лог
public class Main {
    private static HashSet<edu.core.java.auction.domain.interfaces.Seller> sellers = new HashSet<>();
    private static HashSet<edu.core.java.auction.domain.interfaces.Auctioneer> auctioneers = new HashSet<>();
    private static HashSet<edu.core.java.auction.domain.interfaces.Buyer> buyers = new HashSet<edu.core.java.auction.domain.interfaces.Buyer>();
    private static HashSet<Lot> lots = new HashSet<>();
    private static HashSet<Bid> bids = new HashSet<>();
    private static HashSet<edu.core.java.auction.domain.interfaces.Product> products = new HashSet<>();

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
                    switch(choice){
                        case 1 :
                            System.out.print("Name: ");
                            String aucName = reader.readLine();
                            edu.core.java.auction.domain.interfaces.Auctioneer auctioneer = service.createSimpleAuctioneer(aucName);
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
                            edu.core.java.auction.domain.interfaces.Product product = service.createSimpleProduct(
                                    prodTitle, prodDescr, Long.parseLong(prodOwnerId), Double.parseDouble(prodSellerPrice));
                            products.add(product);
                            System.out.println("Product with id = " + product.getId() + " was created.");
                            break;
                        case 3 :
                            System.out.println("Name: ");
                            String buyerName = reader.readLine();
                            System.out.println("Account balance: ");
                            String buyerAccountBalance = reader.readLine();
                            edu.core.java.auction.domain.interfaces.Buyer buyer = service.createSimpleBuyer(buyerName, Double.parseDouble(buyerAccountBalance));
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
                            edu.core.java.auction.domain.interfaces.Seller seller = service.createSimpleSeller(
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
                    Long id;
                    switch(choice){
                        case 1 :
                            System.out.print("Enter auctioneer ID: ");
                            id = Long.parseLong(reader.readLine());
                            service.deleteSimpleAuctioneerById(id);
                            System.out.println("Auctioneer with id = " + id + " was deleted.");
                            break;
                        case 2 :
                            System.out.print("Enter product ID: ");
                            id = Long.parseLong(reader.readLine());
                            service.deleteSimpleProductById(id);
                            System.out.println("Product with id = " + id + " was deleted.");
                            break;
                        case 3 :
                            System.out.print("Enter buyer ID: ");
                            id = Long.parseLong(reader.readLine());
                            service.deleteSimpleBuyerById(id);
                            System.out.println("Buyer with id = " + id + " was deleted.");
                            break;
                        case 4 :
                            System.out.print("Enter seller ID: ");
                            id = Long.parseLong(reader.readLine());
                            service.deleteSimpleSellerById(id);
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
                    choice = Integer.parseInt(reader.readLine());
//                    switch(choice){
//                        // TODO
//                    }
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
                    switch(choice){
                        case 1 :
                            System.out.print("Enter auctioneer ID: ");
                            id = Long.parseLong(reader.readLine());
                            Auctioneer auctioneer = service.getSimpleAuctioneerById(id);
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
                            Product simpleProduct = service.getSimpleProductById(id);
                            System.out.println(simpleProduct.getId());
                            System.out.println(simpleProduct.getTitle());
                            System.out.println(simpleProduct.getDescription());
                            System.out.println("Owner ID: " + simpleProduct.getOwner().getId());
                            System.out.println("Seller price: " + simpleProduct.getSellerPrice());
                            break;
                        case 3 :
                            System.out.print("Enter buyer ID: ");
                            id = Long.parseLong(reader.readLine());
                            Buyer buyer = service.getSimpleBuyerById(id);
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
                            Seller seller = service.getSimpleSellerById(id);
                            System.out.println(seller.getId());
                            System.out.println(seller.getName());
                            System.out.println(seller.getAccountBalance());
                            System.out.println(seller.getComissionPercentage());
                            System.out.println("Product IDs: ");
                            for (edu.core.java.auction.domain.interfaces.Product product : seller.getProducts()){
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
                    break;
                case 5 :
                    return;
            }
        }

        Timer timer = new Timer();
       // timer.schedule(new Checker(auctioneer, seller, buyer1, buyer2, product, lot), 5000);
    }

    public static class Checker extends TimerTask{
        edu.core.java.auction.domain.interfaces.Auctioneer auctioneer;
        edu.core.java.auction.domain.interfaces.Seller seller;
        edu.core.java.auction.domain.interfaces.Buyer buyer1;
        edu.core.java.auction.domain.interfaces.Buyer buyer2;
        edu.core.java.auction.domain.interfaces.Product product;
        Lot lot;

        public Checker(edu.core.java.auction.domain.interfaces.Auctioneer auc, edu.core.java.auction.domain.interfaces.Seller sel, edu.core.java.auction.domain.interfaces.Buyer buy1, edu.core.java.auction.domain.interfaces.Buyer buy2, edu.core.java.auction.domain.interfaces.Product pro, Lot lot){
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
