package Plant.IamGroot.entity;

import Plant.IamGroot.constant.ItemSellStatus;
import Plant.IamGroot.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "item")
public class Item extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;  // 상품 코드

    @Column(nullable = false, length = 500)
    private String itemName;  // 상품명

    @Column(name = "price", nullable = false)
    private int price;  // 상품 가격

    @Column(nullable = false)
    private int stockNumber;  // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

}
