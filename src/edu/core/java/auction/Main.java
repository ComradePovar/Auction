package edu.core.java.auction;

import edu.core.java.auction.domain.concrete.Bid;
import edu.core.java.auction.domain.concrete.Lot;
import edu.core.java.auction.domain.concrete.SimpleSeller;
import edu.core.java.auction.domain.interfaces.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
// TODO:
// Добавить удаление, обновление, вывод сущностей
// Упростить модель
// ПЕРЕПРОВЕРИТЬ ВСЕ
// Добавить JSON и лог
public class Main {
    private static HashSet<Seller> sellers = new HashSet<>();
    private static HashSet<Auctioneer> auctioneers = new HashSet<>();
    private static HashSet<Buyer> buyers = new HashSet<Buyer>();
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

            switch(Integer.parseInt(reader.readLine())) {
                case 1 :
                    System.out.println("1. Create auctioneer.");
                    System.out.println("2. Create product.");
                    System.out.println("3. Create buyer.");
                    System.out.println("4. Create seller.");
                    System.out.println("5. Create lot.");
                    System.out.println("6. Create bid.");
                    System.out.println("7. Main menu.");
                    System.out.println("----------------------------------------------------");
                    String buffer;
                    switch(Integer.parseInt(reader.readLine())){
                        case 1 :
                            System.out.print("Name: ");
                            String aucName = reader.readLine();
                            Auctioneer auctioneer = service.createSimpleAuctioneer(aucName);
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
                            Product product = service.createSimpleProduct(
                                    prodTitle, prodDescr, Long.parseLong(prodOwnerId), Double.parseDouble(prodSellerPrice));
                            products.add(product);
                            System.out.println("Product with id = " + product.getId() + " was created.");
                            break;
                        case 3 :
                            System.out.println("Name: ");
                            String buyerName = reader.readLine();
                            System.out.println("Account balance: ");
                            String buyerAccountBalance = reader.readLine();
                            Buyer buyer = service.createSimpleBuyer(buyerName, Double.parseDouble(buyerAccountBalance));
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
                            Seller seller = service.createSimpleSeller(
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
                            System.out.println("Lot with id = " + lot.getId() + " was created.");
                            break;
                        case 6 :
                            System.out.println("Buyer ID: ");
                            String bidBuyerId = reader.readLine();
                            System.out.println("Bid amount: ");
                            String bidAmount = reader.readLine();
                            System.out.println("Lot ID: ");
                            String bidLotId = reader.readLine();
                            Bid bid = service.createBid(
                                    Long.parseLong(bidBuyerId), Long.parseLong(bidLotId), Double.parseDouble(bidAmount));
                            bids.add(bid);
                            System.out.println("Bid with id = " + bid.getId() + " was created.");
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
                    System.out.println("----------------------------------------------------");
                    break;
                case 3 :
                    System.out.println("1. Update auctioneer.");
                    System.out.println("2. Update product.");
                    System.out.println("3. Update buyer.");
                    System.out.println("4. Update seller.");
                    System.out.println("5. Update lot.");
                    System.out.println("6. Update bid.");
                    System.out.println("----------------------------------------------------");
                    break;
                case 4 :
                    System.out.println("1. Show auctioneer.");
                    System.out.println("2. Show product.");
                    System.out.println("3. Show buyer.");
                    System.out.println("4. Show seller.");
                    System.out.println("5. Show lot.");
                    System.out.println("6. Show bid.");
                    System.out.println("----------------------------------------------------");
                    break;
                case 5 :
                    return;
            }
        }

        Timer timer = new Timer();
       // timer.schedule(new Checker(auctioneer, seller, buyer1, buyer2, product, lot), 5000);
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
