package client.service;

import client.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAll() throws Exception;
}
