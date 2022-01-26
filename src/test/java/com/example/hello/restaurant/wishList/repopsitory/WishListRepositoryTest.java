package com.example.hello.restaurant.wishList.repopsitory;

import com.example.hello.restaurant.wishList.entity.WishListEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;


    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setReadAddress("readAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest(){

        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        assertNotNull(expected);
        assertEquals(1, expected.getIndex());
    }


    @Test
    public void findByIdTest(){

        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1);

        assertEquals(true, expected.isPresent());
        assertEquals(1,expected.get().getIndex());

    }

    @Test
    public void deleteTest(){
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1);

        int count = wishListRepository.listAll().size();

        assertEquals(0,count);
    }

    @Test
    public void listAllTest(){

        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int count = wishListRepository.listAll().size();

        assertEquals(2,count);
    }


    @Test
    public void updateTest(){

        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        expected.setTitle("update test");
        var saveEntity = wishListRepository.save(expected);

        assertNotNull(expected);
        assertEquals("update test", saveEntity.getTitle());
        assertEquals(1,wishListRepository.listAll().size());
    }


}