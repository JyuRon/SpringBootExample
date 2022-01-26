package com.example.hello.restaurant.wishList.repopsitory;

import com.example.hello.restaurant.db.MemoryDbRepositoryAbstract;
import com.example.hello.restaurant.wishList.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {

}
