package edu.miu.cs545.group01.online.market.service;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.GainPoint;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.enums.GainPointType;

public interface GainPointService {
    float getFreePoints(Buyer buyer);

    GainPoint makePoints(GainPointType type, Buyer buyer, Order order, float point);
}
