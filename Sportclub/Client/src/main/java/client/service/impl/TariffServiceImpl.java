package client.service.impl;

import client.entity.Tariff;
import client.reposetory.TariffRepository;
import client.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TariffServiceImpl implements TariffService {

    @Autowired
    public TariffRepository tariffRepository;
    @Autowired
    @Override
    public List<Tariff> getAll() throws Exception {

        return tariffRepository.findAll();
    }
}



