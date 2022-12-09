package com.dec22.cbt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeMessageRepository extends JpaRepository<Trademessage, String> {
}