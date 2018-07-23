package IOStuff.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MakeAFile
{
    public static void main(String[] args)
    {
        List<AmazonPurchase> purchaseList = new ArrayList<>();

        File file = new File("C:\\IdeaProjects\\JavaFeatureExplore\\src\\main\\java\\IOStuff\\File\\amazon.csv");
        try (BufferedReader in = new BufferedReader(new FileReader(file)))
        {
            String line;
            in.readLine();
            while ((line = in.readLine()) != null)
                purchaseList.add(createItem(line));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println(file.isFile());
    }

    public static AmazonPurchase createItem(String line)
    {
        //this is to just get rid of any lines that have only commas
        if (!line.contains("a"))
            return null;
        String[] parts = getCSVLineItem(line);

        AmazonPurchase purchase = new AmazonPurchase();

        LocalDate ld = convertStringToLocalDate(parts[0]);
        purchase.setOrderDate(ld);

        purchase.setOrderId(parts[1]);
        purchase.setTitle(parts[2]);
        purchase.setCategory(parts[3]);
        purchase.setCondition(parts[8]);
        purchase.setSeller(parts[9]);

        BigDecimal listPrice = BigDecimal.ZERO;
        String listPriceString = parts[11].replace("$","").trim();
        if (listPriceString.length() > 0)
            listPrice = new BigDecimal(listPriceString);
        purchase.setListPrice(listPrice);

        BigDecimal purchasePrice = BigDecimal.ZERO;
        String purchasePriceString = parts[12].replace("$","").trim();
        if (purchasePriceString.length() > 0)
            purchasePrice = new BigDecimal(purchasePriceString);
        purchase.setPurchase(purchasePrice);

        purchase.setQuantity(Integer.valueOf(parts[13]));
        purchase.setShippingAddress(parts[20]);

        BigDecimal tax = BigDecimal.ZERO;
        String taxString = parts[28].replace("$","").trim();
        if (taxString.length() > 0)
            tax = new BigDecimal(taxString);
        purchase.setTax(tax);

        BigDecimal itemTotal = BigDecimal.ZERO;
        String itemTotalString = parts[29].replace("$","").trim();
        if (itemTotalString.length() > 0)
            itemTotal = new BigDecimal(itemTotalString);
        purchase.setItemTotal(itemTotal);

        return purchase;
    }

    public static LocalDate convertStringToLocalDate(String date)
    {
        if (date.length() > 0)
        {
            String[] parts = date.split(",");

            int month = Integer.valueOf(parts[0]);
            int day = Integer.valueOf(parts[1]);
            int year = Integer.valueOf(parts[2]);

            LocalDate ld = LocalDate.of(year, month, day);
            return ld;
        }
        return null;
    }

    public static String[] getCSVLineItem(String line)
    {
        String[] parts = new String[35];
        char[] chars = line.toCharArray();

        String parsedLine = "";

        int partCounter = 0;

        boolean insideQuote = false;
        for (int i = 0; i < chars.length; i++)
        {
            char c = chars[i];
            if (c == '\"')
            {
                if (insideQuote == true)
                    insideQuote = false;
                else
                    insideQuote = true;

                continue;
            }

            if (c == ',' && !insideQuote)
            {
                parts[partCounter] = parsedLine;
                partCounter++;
                parsedLine = "";
                continue;
            }

            parsedLine += c;
        }

        return parts;
    }
}
