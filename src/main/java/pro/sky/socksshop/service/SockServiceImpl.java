package pro.sky.socksshop.service;

import org.springframework.stereotype.Service;
import pro.sky.socksshop.exception.SockNotFoundException;
import pro.sky.socksshop.model.Sock;

import java.util.HashMap;
import java.util.Map;

@Service
public class SockServiceImpl implements SockService {
    private static final Map<Sock, Integer> socks = new HashMap<>();

    @Override
    public int add(Sock sock, int quantity) {
        if (socks.containsKey(sock)) {
            int value = socks.get(sock) + quantity;
            socks.put(sock, value);
            return value;
        } else {
            socks.put(sock, quantity);
            return quantity;
        }
    }

    @Override
    public int remove(Sock sock, int quantity) throws SockNotFoundException {
        if (socks.containsKey(sock)) {
            int value = socks.get(sock) - quantity;
            if (value <= 0) {
                socks.remove(sock);
                return 0;
            }
            socks.put(sock, value);
            return value;
        } else {
            throw new SockNotFoundException("Нет столько товара на складе!");
        }
    }

    @Override
    public int getMin(Sock sock) {
        int counter = 0;
        for (Sock element : socks.keySet()) {
            if (element.getCottonPart() > sock.getCottonPart()) {
                counter += socks.get(element);
            }
        }
        return counter;
    }

    @Override
    public int getMax(Sock sock) {
        int counter = 0;
        for (Sock element : socks.keySet()) {
            if (element.getCottonPart() < sock.getCottonPart()) {
                counter += socks.get(element);
            }
        }
        return counter;
    }
}
