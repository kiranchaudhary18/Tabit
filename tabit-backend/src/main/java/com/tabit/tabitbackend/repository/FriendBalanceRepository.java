package com.tabit.tabitbackend.repository;

import com.tabit.tabitbackend.model.FriendBalance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendBalanceRepository extends MongoRepository<FriendBalance, String> {

    List<FriendBalance> findByUserId(String userId);

    List<FriendBalance> findByFriendId(String friendId);

    List<FriendBalance> findByUserIdAndFriendId(String userId, String friendId);
}
