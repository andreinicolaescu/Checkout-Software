package services;
import model.Product;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CasaDeMarcat {

    private Database database;
    private Map<Product, Integer> cart;
    private boolean init = false;
    private float totalPrice = 0;

    static private String logpath = "D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\log.csv";
    static private Path produceCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\produce.csv");
    static private Path albumCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\albums.csv");
    static private Path clothingCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\clothing.csv");
    static private Path drinkCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\drinks.csv");
    static private Path magazineCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\magazines.csv");
    static private Path bookCSV = Paths.get("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Checkout Software\\src\\main\\java\\data\\books.csv");

    public Database getDatabase() {
        return database;
    }

    public void init() {
        database = new Database();

        /*

        File filename = new File("D:\\CodeBlocks\\Projects\\ANUL II\\SEM II\\Java (PAO)\\Project\\src\\service\\products.txt");

        try{
            database.addProducts(filename);
        } catch (IOException e) {                       // from txt file
            e.printStackTrace();
        }

        */

        database.addProductsFromCSV(produceCSV);
        database.addProductsFromCSV(albumCSV);
        database.addProductsFromCSV(clothingCSV);
        database.addProductsFromCSV(drinkCSV);                   // from CSV file
        database.addProductsFromCSV(magazineCSV);
        database.addProductsFromCSV(bookCSV);

        cart = new HashMap<Product, Integer>();
        init = true;

        try {

            PrintWriter w = new PrintWriter(new File(logpath));
            w.print("");                                            // CLEAR LOG ON LAUNCH
            w.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        writeLog("DATABASE_INIT", null);

    }

    public void updateLocal() {

        database.reset();

        database.addProductsFromCSV(produceCSV);
        database.addProductsFromCSV(albumCSV);
        database.addProductsFromCSV(clothingCSV);
        database.addProductsFromCSV(drinkCSV);
        database.addProductsFromCSV(magazineCSV);
        database.addProductsFromCSV(bookCSV);

        writeLog("DATABASE_UPDATE", null);
    }

    public void writeLog(String desc, Product product) {
        try
        {
            FileWriter fw = new FileWriter(logpath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            StringBuffer oneLine = new StringBuffer();

            if(product != null) {
                oneLine.append(desc);
                oneLine.append(",");
                oneLine.append(product.getId());
                oneLine.append(",");
                oneLine.append(product.price);
                oneLine.append(",");
                oneLine.append(strDate);
            } else {
                oneLine.append(desc);
                oneLine.append(",");
                oneLine.append(strDate);
            }

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}

    }

    public List<Product> showDatabase() {

        List<Product> returned = new ArrayList<Product>();

        if(init == true) returned = database.showProducts();
        else {
            System.out.println("Database not initalised...\n");
            throw new ExceptionInInitializerError("Database not initialised...");
        }

        return returned;
    }

    public List<Product> showCart() {

        List<Product> returned = new ArrayList<>();

        System.out.println("Current cart...\n");
        for (Product i : cart.keySet()) {
            i.print();
            int quantity = cart.get(i);
            System.out.printf("QUANTITY : %d\n", cart.get(i));

            for (int j=0; j<quantity; j++) {
                returned.add(i);
            }

        }

        return returned;
    }

    public Product scanProductById(int id) {

        //Scanner idscan = new Scanner(System.in);
        //System.out.println("Scan product by ID : ");
        //int id = Integer.parseInt(idscan.nextLine());
        Product scannedProduct = database.getById(id);

        if( cart.containsKey(scannedProduct)) {
            cart.put(scannedProduct, cart.get(scannedProduct) + 1);
        }
        else cart.put(scannedProduct, 1);

        System.out.println("This product has been added...");
        scannedProduct.print();
        System.out.printf("QUANTITY : %d\n", cart.get(scannedProduct));

        // write to CSV

        writeLog("SCAN", scannedProduct);

        return scannedProduct;

    }

    public int getQuantity (int id) {
        Product product = database.getById(id);

        if (cart.containsKey(product)) {
            return cart.get(product);
        }
        else return 0;
    }

    public void removeProductById(int id) {
        Scanner idscan = new Scanner(System.in);

        boolean OK = false;
        //int id = -1;
        Product productToBeRemoved = null;

        while(OK == false) {

            System.out.println("Remove product by ID : ");
            //id = Integer.parseInt(idscan.nextLine());
            productToBeRemoved = database.getById(id);

            if (cart.containsKey(productToBeRemoved) && cart.get(productToBeRemoved) == 1) {
                cart.remove(productToBeRemoved);
                System.out.println("This product has been removed from cart...\n");
                productToBeRemoved.print();
                OK = true;
            } else if (cart.containsKey(productToBeRemoved) && cart.get(productToBeRemoved) > 1) {
                cart.put(productToBeRemoved, cart.get(productToBeRemoved) - 1);
                System.out.println("This product has been removed from cart...\n");
                productToBeRemoved.print();
                OK = true;
            } else {
                System.out.println("ERROR! Specified product not found in cart...\nTry again...");
            }
        }

        writeLog("REMOVED", productToBeRemoved);

    }

    public float totalPrice() {

        totalPrice = 0;

        for (Product i : cart.keySet()) {
            totalPrice += i.price * cart.get(i);
        }

        System.out.printf("Total price is of %f LEI\n", totalPrice);
        return totalPrice;
    }

    public void pay() {

        boolean enough_money = false;
        Scanner payment;
        float paid = 0;
        float remainder = 0;

        while (enough_money == false) {

            System.out.println("Waiting for payment...");

            payment = new Scanner(System.in);
            paid += Integer.parseInt(payment.nextLine());

            System.out.printf("Customer paid %f LEI.\n", paid);

            if(paid < totalPrice) {
                System.out.println("Not enough money... Pay more!\n");
            }
            else if(paid == totalPrice) {
                System.out.println("Payment complete, no remainder.\n");
                enough_money = true;
            }
            else {
                remainder = paid - totalPrice;
                System.out.printf("Payment complete.\nRemainder is of %f LEI.\n", remainder);
                enough_money = true;
            }

        }

        writeLog("PAYMENT_COMPLETE", null);

        System.out.println("Thank you for your purcahse! We hope to see you again!\n");

    }

    public void newClient() {
        cart.clear();
        totalPrice = 0;

        System.out.println("Next client...\n\n");
    }

}
