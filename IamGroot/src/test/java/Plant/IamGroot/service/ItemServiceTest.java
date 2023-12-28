package Plant.IamGroot.service;

import Plant.IamGroot.constant.ItemSellStatus;
import Plant.IamGroot.dto.ItemFormDto;
import Plant.IamGroot.entity.Item;
import Plant.IamGroot.entity.ItemImg;
import Plant.IamGroot.repository.ItemImgRepository;
import Plant.IamGroot.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemImgRepository itemImgRepository;

    @Autowired
    ItemRepository itemRepository;

    List<MultipartFile> createMultipartFiles() throws Exception{
        List<MultipartFile> multipartFileList = new ArrayList<>();

        for(int i=0; i<5; i++){
            String path = "C:/Study/IamGrootImg/";
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile =
                    new MockMultipartFile(path, imageName, "image/jpg", new byte[]{1, 2, 3, 4, 5});
            multipartFileList.add(multipartFile);
        }
        return multipartFileList;
    }


    @Test
    @DisplayName("상품 등록 테스트")
    @WithMockUser(username = "BK", roles = "ADMIN")
    void saveItem() throws Exception{
        ItemFormDto itemFormDto = new ItemFormDto();

        itemFormDto.setItemName("테스트상품");
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        itemFormDto.setItemDetail("테스트 상품 입니다.");
        itemFormDto.setPrice(1000);
        itemFormDto.setStockNumber(100);
        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long itemId = itemService.saveItem(itemFormDto, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        Assertions.assertEquals(itemFormDto.getItemName(), item.getItemName());
        Assertions.assertEquals(itemFormDto.getItemSellStatus(), item.getItemSellStatus());
        Assertions.assertEquals(itemFormDto.getPrice(), item.getPrice());
        Assertions.assertEquals(itemFormDto.getStockNumber(), item.getStockNumber());
        System.out.println("multipartFileList.get(0).getOriginalFilename() = " + multipartFileList.get(0).getOriginalFilename());
        System.out.println("itemImgList.get(0).getOriImgName() = " + itemImgList.get(0).getOriImgName());
        Assertions.assertEquals(multipartFileList.get(1).getOriginalFilename(), itemImgList.get(0).getOriImgName());

    }

}