package services;

import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Database {

    private static ArrayList<Product> database = new ArrayList<Product>();

    public Product checktxt(String current_line) {
        Scanner words = new Scanner(current_line);
        while (words.hasNext()) {

            String product_type = words.next();

            switch(product_type) {
                case "ALBUM":

                    Album.albumType t1 = Album.albumType.valueOf(words.next());
                    String albumtitle = words.next();
                    String artist = words.next();
                    int nr = Integer.parseInt(words.next());
                    float price = Integer.parseInt(words.next());

                    Album a = new Album(t1, albumtitle, artist, nr, price, CurrentID.getInstance().id);
                    return a;

                case "BOOK":

                    Book.bookType t2 = Book.bookType.valueOf(words.next());
                    String author = words.next();
                    String booktitle = words.next();
                    price = Integer.parseInt(words.next());

                    Book b = new Book(t2, author, booktitle, price, CurrentID.getInstance().id);
                    return b;

                case "CLOTHING":

                    Clothing.clothingType ct = Clothing.clothingType.valueOf(words.next());
                    Clothing.clothingPart cp = Clothing.clothingPart.valueOf(words.next());
                    String brand = words.next();
                    String name = words.next();
                    price = Integer.parseInt(words.next());

                    Clothing c = new Clothing(brand, name, cp, ct, price, CurrentID.getInstance().id);
                    return c;

                case "DRINK":

                    Drink.drinkType dt = Drink.drinkType.valueOf(words.next());
                    brand = words.next();
                    int q = Integer.parseInt(words.next());
                    price = Integer.parseInt(words.next());

                    Drink d = new Drink(brand, q, dt, price, CurrentID.getInstance().id);
                    return d;

                case "MAGAZINE":

                    Magazine.magazineType mt = Magazine.magazineType.valueOf(words.next());
                    name = words.next();
                    price = Integer.parseInt(words.next());

                    Magazine m = new Magazine(name, mt, price, CurrentID.getInstance().id);
                    return m;

                case "PRODUCE":

                    Produce.produceType pt = Produce.produceType.valueOf(words.next());
                    name = words.next();
                    brand = words.next();
                    price = Integer.parseInt(words.next());

                    Produce p = new Produce(brand, name, pt, price, CurrentID.getInstance().id);
                    return p;

            }

        }
        return null;
    }

    public void addProducts(File filename) throws FileNotFoundException {

        Scanner lines = new Scanner(filename);
        String current_line;
        String product_type;

        while (lines.hasNextLine()) {

            if ((current_line = lines.nextLine()) != null) {

            database.add(checktxt(current_line));

            }

        }
    }

    private Product splitAndAdd(String line) {
        String[] words = line.split(",");

        String product_type = words[0];

        switch(words[0]) {
            case "ALBUM":

                Album.albumType t1 = Album.albumType.valueOf(words[1]);
                String albumtitle = words[2];
                String artist = words[3];
                int nr = Integer.parseInt(words[4]);
                float price = Integer.parseInt(words[5]);

                Album a = new Album(t1, albumtitle, artist, nr, price, CurrentID.getInstance().id);
                return a;

            case "BOOK":

                Book.bookType t2 = Book.bookType.valueOf(words[1]);
                String author = words[2];
                String booktitle = words[3];
                price = Integer.parseInt(words[4]);

                Book b = new Book(t2, author, booktitle, price, CurrentID.getInstance().id);
                return b;

            case "CLOTHING":

                Clothing.clothingType ct = Clothing.clothingType.valueOf(words[1]);
                Clothing.clothingPart cp = Clothing.clothingPart.valueOf(words[2]);
                String brand = words[3];
                String name = words[4];
                price = Integer.parseInt(words[5]);

                Clothing c = new Clothing(brand, name, cp, ct, price, CurrentID.getInstance().id);
                return c;

            case "DRINK":

                Drink.drinkType dt = Drink.drinkType.valueOf(words[1]);
                brand = words[2];
                int q = Integer.parseInt(words[3]);
                price = Integer.parseInt(words[4]);

                Drink d = new Drink(brand, q, dt, price, CurrentID.getInstance().id);
                return d;

            case "MAGAZINE":

                Magazine.magazineType mt = Magazine.magazineType.valueOf(words[1]);
                name = words[2];
                price = Integer.parseInt(words[3]);

                Magazine m = new Magazine(name, mt, price, CurrentID.getInstance().id);
                return m;

            case "PRODUCE":

                Produce.produceType pt = Produce.produceType.valueOf(words[1]);
                name = words[2];
                brand = words[3];
                price = Integer.parseInt(words[4]);

                Produce p = new Produce(brand, name, pt, price, CurrentID.getInstance().id);
                return p;

        }

        return null;

    }

    public void addProductsFromCSV(Path path) {
        try {
            //Path p = Paths.get("D:/GitHub/CheckoutWindow-Software/Project/src/service/products.csv");
            Files.lines(path)
                    .flatMap(line -> Arrays.stream(line.split(System.getProperty("line.separator"))))
                    .forEach(line -> database.add(splitAndAdd(line)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Product> showProducts () {

        List<Product> returned = new ArrayList<Product>();

        Iterator<Product> i = database.iterator();
        while (i.hasNext()) {
            returned.add(i.next());
        }

        return returned;

    }

    public Product getById(int id) {
        /*Iterator<Product> i = database.iterator();
        Product p;
        while (i.hasNext()) {
            p = i.next();
            if(p.getId()==id) return p;
        }*/
        return database.get(id-1);
    }

    public void reset() {
        CurrentID.reset();
        database.clear();
    }

}

