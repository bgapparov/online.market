package edu.miu.cs545.group01.online.market.service.impl;

import edu.miu.cs545.group01.online.market.domain.Buyer;
import edu.miu.cs545.group01.online.market.domain.GainPoint;
import edu.miu.cs545.group01.online.market.domain.Order;
import edu.miu.cs545.group01.online.market.domain.enums.GainPointType;
import edu.miu.cs545.group01.online.market.repository.GainPointRepository;
import edu.miu.cs545.group01.online.market.service.GainPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GainPointServiceImpl implements GainPointService {
    @Autowired
    private GainPointRepository gainPointRepository;
    @Override
    public float getFreePoints(Buyer buyer) {
        float result = 0;
        List<GainPoint> gainPoints = gainPointRepository.findAllByBuyer(buyer);
        for (GainPoint gainPoint : gainPoints) {
            result += gainPoint.getActualPoint();
        }
        return result;
    }

    @Override
    public GainPoint makePoints(GainPointType type, Buyer buyer, Order order, float point) {
        GainPoint gainPoint = new GainPoint(type, order, buyer, point);
        return gainPointRepository.save(gainPoint);
    }

}
