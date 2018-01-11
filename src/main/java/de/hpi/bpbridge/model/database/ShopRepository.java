package de.hpi.bpbridge.model.database;

import de.hpi.bpbridge.model.database.data.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends MongoRepository<Shop, Long>{

    Shop findByShopID(long shopID);
}