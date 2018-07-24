/**
 *
 * SQL to make the Purchase table in database Amazon using MySQL
 *
 * create table purchases(
 *   ORDER_ID varchar(100),
 *   ORDER_DATE date,
 *   TITLE varchar(1000),
 *   CATEGORY varchar(225),
 *   ITEM_CONDITION varchar(50),
 *   SELLER varchar(225),
 *   LIST_PRICE decimal(10,2),
 *   PURCHASE decimal(10,2),
 *   QUANTITY int,
 *   SHIPPING_ADDRESS varchar(225),
 *   TAX decimal(10,2),
 *   ITEM_TOTAL decimal(10,2),
 *   PRIMARY KEY(ORDER_ID)
 * );
 *
 */

package IOStuff.File;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class AmazonPurchase
{
    @Id
    Integer objectId;

    @Column
    String orderId = "";
    @Convert(converter = LocalDateAttributeConverter.class)
    LocalDate orderDate;
    @Column
    String title = "";
    @Column
    String category = "";
    @Column
    String condition = "";
    @Column
    String seller = "";
    @Column
    BigDecimal listPrice;
    @Column
    BigDecimal purchase;
    @Column
    Integer quantity;
    @Column
    String shippingAddress = "";
    @Column
    BigDecimal tax;
    @Column
    BigDecimal itemTotal;


    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate)
    {
        this.orderDate = orderDate;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }

    public String getSeller()
    {
        return seller;
    }

    public void setSeller(String seller)
    {
        this.seller = seller;
    }

    public BigDecimal getListPrice()
    {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice)
    {
        this.listPrice = listPrice;
    }

    public BigDecimal getPurchase()
    {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase)
    {
        this.purchase = purchase;
    }

    public String getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getTax()
    {
        return tax;
    }

    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }

    public BigDecimal getItemTotal()
    {
        return itemTotal;
    }

    public void setItemTotal(BigDecimal itemTotal)
    {
        this.itemTotal = itemTotal;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }

    public Integer getObjectId()
    {
        return objectId;
    }

    public void setObjectId(Integer objectId)
    {
        this.objectId = objectId;
    }
}
