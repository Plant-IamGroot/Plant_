package Plant.IamGroot.repository;

import Plant.IamGroot.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String ItemName);

    List<Item> findByItemNameOrItemDetail(String ItemName, String ItemDetail);

    List<Item> findByPriceLessThan(Integer Price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDtail);
}
