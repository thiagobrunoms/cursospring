package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cursomc.domain.OrderedItem;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {

}
